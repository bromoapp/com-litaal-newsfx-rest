package com.litaal.newsfx.dao;

import com.litaal.newsfx.model.AccessToken;

public interface IAccessTokenDao {
	
	public boolean create(AccessToken obj);

	public AccessToken select(String token);

	public boolean delete(String token);
}
