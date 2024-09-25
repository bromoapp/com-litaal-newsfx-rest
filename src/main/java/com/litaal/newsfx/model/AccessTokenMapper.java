package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessTokenMapper extends ValueMapperBase<AccessToken> {

	@Override
	public AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
		AccessToken obj = new AccessToken();
		if (isColumnExists(rs, "token")) {
			obj.setToken(rs.getString("token"));
		}
		if (isColumnExists(rs, "expired")) {
			obj.setExpired(rs.getLong("expired"));
		}
		return obj;
	}

}
