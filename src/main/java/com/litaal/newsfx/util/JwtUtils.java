package com.litaal.newsfx.util;

import java.time.ZonedDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.dao.AccessTokenDaoImpl;
import com.litaal.newsfx.model.AccessToken;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	Logger log = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.validity}")
	private int validity;
	
	@Autowired
	private AccessTokenDaoImpl dao;

	public String getNewToken(Authentication auth) {
		UserDetails details = (UserDetails) auth.getPrincipal();
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(details.getUsername());
		builder.setIssuedAt(new Date());
		Date expired = Date.from(ZonedDateTime.now().plusHours(validity).toInstant());
		builder.setExpiration(expired);
		builder.signWith(SignatureAlgorithm.HS512, secret);
		String token = builder.compact();
		dao.create(new AccessToken(token, expired.getTime()));
		return token;
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public void validateJwtToken(String authToken) throws Exception {
		AccessToken token = dao.select(authToken);
		if (token != null) {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token.getToken());
		} else {
			Jwts.parser().setSigningKey(secret).parseClaimsJws("EMPTY");
		}
	}
}
