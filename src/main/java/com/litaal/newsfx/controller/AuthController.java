package com.litaal.newsfx.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.eventbus.EventBus;
import com.google.common.io.Files;
import com.litaal.newsfx.constant.EResponseType;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.dto.AccessTokenDto;
import com.litaal.newsfx.dto.EmailMessage;
import com.litaal.newsfx.dto.NewUserDto;
import com.litaal.newsfx.dto.ResponseDto;
import com.litaal.newsfx.model.UserInfo;
import com.litaal.newsfx.service.ICredentialService;
import com.litaal.newsfx.service.IUserInfoService;
import com.litaal.newsfx.util.CommonUtil;
import com.litaal.newsfx.util.JwtUtils;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

	@Value("classpath:newuser.html")
	Resource newuserHtml;

	@Value("classpath:resetpassw.html")
	Resource resetpasswHtml;

	@Autowired
	private Environment env;

	@Autowired
	private CommonUtil util;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private ICredentialService credentialService;

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private EventBus eventBus;

	@Value("${jwt.validity}")
	private int validity;

	/**
	 * Endpoint for user login.
	 * 
	 * @param uname The username
	 * @param pass  The password
	 * @return A response entity with the access token if successful, otherwise a
	 *         401 Unauthorized response
	 */
	@PostMapping(path = "login")
	public ResponseEntity<ResponseDto> login(@RequestParam("username") String uname,
			@RequestParam("password") String pass) {
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(uname, pass));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String validUntil = util.getExpiryTime(validity);
			String jwt = jwtUtils.getNewToken(auth);
			return ResponseEntity.ok(new AccessTokenDto(jwt, validUntil));
		} catch (BadCredentialsException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponseDto(EResponseType.ERROR, "Not authorized"));
		}
	}

	/**
	 * Endpoint for user logout.
	 * 
	 * @param token The JWT token
	 * @return A response entity with a message indicating successful logout
	 */
	@PostMapping(path = "logout")
	public ResponseEntity<ResponseDto> logout(@RequestParam("token") String token) {
		credentialService.logout(token);
		return ResponseEntity.ok(new ResponseDto(EResponseType.INFO, "Logout succeed"));
	}

	/**
	 * Endpoint for user registration.
	 * 
	 * @param fname   The first name of the user
	 * @param lname   The last name of the user
	 * @param gender  The gender of the user
	 * @param birth   The birth year of the user
	 * @param job     The job of the user
	 * @param country The country of the user
	 * @param email   The email address of the user
	 * @return A response entity with a message indicating successful registration
	 */
	@PostMapping(path = "register")
	public ResponseEntity<ResponseDto> register(@RequestParam("fname") String fname,
			@RequestParam("lname") String lname, @RequestParam("gender") String gender,
			@RequestParam("salutation") String salutation, @RequestParam("birth") Long birth,
			@RequestParam("job") String job, @RequestParam("country") String country,
			@RequestParam("email") String email) throws Exception {
		if (!credentialService.isEmailExists(email)) {
			ResponseDto resp = new ResponseDto();
			NewUserDto obj = new NewUserDto();
			obj.setFirstName(fname);
			obj.setLastName(lname);
			obj.setGender(gender);
			obj.setSalutation(salutation);
			obj.setBirthYear(birth);
			obj.setJobTitle(job);
			obj.setCountry(country);
			obj.setEmail(email);

			UserInfo ui = userInfoService.register(obj);
			if (ui != null) {
				String password = util.newRandomPassword();
				if (credentialService.register(ui.getUuid(), obj.getEmail(), password)) {
					List<String> lines = Files.readLines(newuserHtml.getFile(), StandardCharsets.UTF_8);
					String raw = String.join("", lines);
					String content = String.format(raw, email, password);

					resp.setType(EResponseType.INFO);
					resp.setMessage("Your account created");

					EmailMessage msg = new EmailMessage();
					msg.setSender(env.getProperty(Property.MailConfig.ADMIN_USERNAME));
					msg.setRecipient(email);
					msg.setSubject(env.getProperty(Property.MailConfig.NEW_USER_SUBJECT));
					msg.setContent(content);
					eventBus.post(msg);
				} else {
					resp.setType(EResponseType.ERROR);
					resp.setMessage("New account creation failed");
				}
			}
			return ResponseEntity.ok(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDto(EResponseType.ERROR, "Email already exists!"));
		}
	}

	/**
	 * Endpoint for password reset.
	 * 
	 * @param email The email address of the user
	 * @return A response entity with a message indicating successful password reset
	 */
	@PostMapping(path = "reset")
	public ResponseEntity<ResponseDto> reset(@RequestParam("email") String email) throws Exception {
		if (credentialService.isEmailExists(email)) {
			String password = util.newRandomPassword();
			if (credentialService.reset(email, password)) {
				List<String> lines = Files.readLines(resetpasswHtml.getFile(), StandardCharsets.UTF_8);
				String raw = String.join("", lines);
				String content = String.format(raw, email, password);

				EmailMessage msg = new EmailMessage();
				msg.setSender(env.getProperty(Property.MailConfig.ADMIN_USERNAME));
				msg.setRecipient(email);
				msg.setSubject(env.getProperty(Property.MailConfig.RESET_PASSW_SUBJECT));
				msg.setContent(content);
				eventBus.post(msg);

				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseDto(EResponseType.INFO, "New password sent to your email"));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDto(EResponseType.ERROR, "Failed to reset!"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseDto(EResponseType.ERROR, "Email not exists!"));
		}
	}

}
