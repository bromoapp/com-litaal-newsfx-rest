package com.litaal.newsfx;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.util.AuthEntryPointJwt;
import com.litaal.newsfx.util.AuthTokenFilter;

@Configuration
@ComponentScan("com.litaal.newsfx")
@PropertySource("classpath:application.properties")
@EnableWebSecurity
@EnableMethodSecurity
@EnableAsync
public class AppConfig extends AsyncConfigurerSupport {

	@Autowired
	private Environment env;

	/********************************************************************
	 * Event bus using Guava
	 ********************************************************************/
	@Bean
	public EventBus asyncEventBus() {
		return new AsyncEventBus(getAsyncExecutor());
	}

	/********************************************************************
	 * Task executor configurations for asynchronous operations
	 ********************************************************************/
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(Integer.parseInt(env.getProperty(Property.PoolConfig.CORE_SIZE)));
		taskExecutor.setMaxPoolSize(Integer.parseInt(env.getProperty(Property.PoolConfig.MAX_SIZE)));
		taskExecutor
				.setWaitForTasksToCompleteOnShutdown(Boolean.parseBoolean(env.getProperty(Property.PoolConfig.WAIT)));
		taskExecutor.setAwaitTerminationSeconds(Integer.parseInt(env.getProperty(Property.PoolConfig.DELAY)));
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return super.getAsyncUncaughtExceptionHandler();
	}

	/********************************************************************
	 * Database related configurations
	 ********************************************************************/
	@Bean(name = "news_db")
	DataSource newsDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(env.getProperty(Property.DbConn.PROPERTY_NEWSDB_URL));
		ds.setUsername(env.getProperty(Property.DbConn.PROPERTY_NEWSDB_USERNAME));
		ds.setPassword(env.getProperty(Property.DbConn.PROPERTY_NEWSDB_PASSWORD));
		ds.setDriverClassName(env.getProperty(Property.DbConn.PROPERTY_DRIVER));
		return ds;
	}

	@Bean(name = "user_db")
	DataSource userDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(env.getProperty(Property.DbConn.PROPERTY_USERDB_URL));
		ds.setUsername(env.getProperty(Property.DbConn.PROPERTY_USERDB_USERNAME));
		ds.setPassword(env.getProperty(Property.DbConn.PROPERTY_USERDB_PASSWORD));
		ds.setDriverClassName(env.getProperty(Property.DbConn.PROPERTY_DRIVER));
		return ds;
	}

	/********************************************************************
	 * Security related configurations
	 ********************************************************************/
	private static String[] PERMIT_ALL = { "/api/auth/**" };
	private static String[] AUTHORIZED = { "/api/events/**", "/api/prices/**" };
	private static String[] AUTHENTICATED = { "/api/user/**", "/api/events/**", "/api/prices/**" };

	@Value("${authorities}")
	private String[] AUTHORITIES;

	@Autowired
	private AuthEntryPointJwt authEntryPoint;

	@Bean
	AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authManager(HttpSecurity http, AuthenticationProvider provider) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(provider);
		return builder.build();
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider(PasswordEncoder encoder, UserDetailsService service) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(service);
		return provider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter filter) throws Exception {
		http = http.cors().and().csrf().disable();
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		http = http.exceptionHandling().authenticationEntryPoint(authEntryPoint).and();
		http = http.authorizeRequests().antMatchers(PERMIT_ALL).permitAll().and().authorizeRequests()
				.antMatchers(AUTHENTICATED).authenticated().and().httpBasic().and()
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http = http.authorizeRequests().antMatchers(AUTHORIZED).hasAnyAuthority(AUTHORITIES).and();
		return http.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/user/**").allowedOrigins("*");
				registry.addMapping("/api/auth/**").allowedOrigins("*");
				registry.addMapping("/api/events/**").allowedOrigins("*");
				registry.addMapping("/api/prices/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
