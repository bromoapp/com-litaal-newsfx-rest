package com.litaal.newsfx.service;

public interface ICredentialService {

	public boolean isEmailExists(String email);

	public boolean reset(String email, String password);

	public boolean register(String uuid, String username, String password);

	public void logout(String token);

}
