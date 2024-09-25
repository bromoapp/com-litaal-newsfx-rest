package com.litaal.newsfx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.litaal.newsfx.dao.ICredentialDao;
import com.litaal.newsfx.dao.IUserInfoDao;
import com.litaal.newsfx.model.Credential;
import com.litaal.newsfx.model.UserInfo;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ICredentialDao credentialDao;

	@Autowired
	private IUserInfoDao userInfoDao;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = parseJwt(request);
		if (jwt != null) {
			try {
				jwtUtils.validateJwtToken(jwt);
				String username = jwtUtils.getUsernameFromToken(jwt);
				Credential cred = credentialDao.findByUsername(username);
				if (cred != null) {
					UserInfo user = userInfoDao.findByUuid(cred.getUuid());
					List<GrantedAuthority> authorities = new ArrayList<>();
					authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

					UserDetails userDetails = new User(cred.getUsername(), cred.getPassword(), cred.getEnabled(), true,
							true, true, authorities);

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					throw new Exception("User not found!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}
