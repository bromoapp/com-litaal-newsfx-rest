package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialMapper extends ValueMapperBase<Credential> {

	@Override
	public Credential mapRow(ResultSet rs, int rowNum) throws SQLException {
		Credential obj = new Credential();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "username")) {
			obj.setUsername(rs.getString("username"));
		}
		if (isColumnExists(rs, "password")) {
			obj.setPassword(rs.getString("password"));
		}
		if (isColumnExists(rs, "enabled")) {
			obj.setEnabled(rs.getBoolean("enabled"));
		}
		return obj;
	}

}
