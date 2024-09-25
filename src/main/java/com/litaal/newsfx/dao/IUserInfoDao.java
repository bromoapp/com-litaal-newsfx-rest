package com.litaal.newsfx.dao;

import com.litaal.newsfx.model.UserInfo;

public interface IUserInfoDao {

	public boolean create(UserInfo user);

	public boolean update(UserInfo user);

	public UserInfo findByUuid(String uuid);
}
