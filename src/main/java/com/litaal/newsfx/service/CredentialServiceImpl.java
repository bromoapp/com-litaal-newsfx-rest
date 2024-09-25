package com.litaal.newsfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.dao.IAccessTokenDao;
import com.litaal.newsfx.dao.ICredentialDao;
import com.litaal.newsfx.model.Credential;

@Component
public class CredentialServiceImpl implements ICredentialService {

	@Autowired
	private IAccessTokenDao accessTokenDao;

	@Autowired
	private ICredentialDao credentialDao;

	@Override
	public boolean isEmailExists(String email) {
		return (credentialDao.findByUsername(email) != null);
	}

	@Override
	public boolean register(String uuid, String username, String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encrypted = encoder.encode(password);
		Credential cred = new Credential();
		cred.setEnabled(true);
		cred.setUuid(uuid);
		cred.setUsername(username);
		cred.setPassword(encrypted);
		return credentialDao.createNew(cred);
	}

	@Override
	public boolean reset(String email, String password) {
		Credential cre = credentialDao.findByUsername(email);
		if (cre != null) {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String encrypted = encoder.encode(password);
			cre.setPassword(encrypted);
			return credentialDao.updatePassword(cre);
		}
		return false;
	}

	@Override
	public void logout(String token) {
		accessTokenDao.delete(token);
	}

}
