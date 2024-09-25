package com.litaal.newsfx.dao;

import com.litaal.newsfx.model.Credential;

public interface ICredentialDao {

	public Credential findByUsername(String email);

	public boolean createNew(Credential obj);

	public boolean updatePassword(Credential obj);

}
