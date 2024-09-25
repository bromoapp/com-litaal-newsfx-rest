package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.litaal.newsfx.constant.ERole;

public class UserInfoMapper extends ValueMapperBase<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfo obj = new UserInfo();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "f_name")) {
			obj.setFirstName(rs.getString("f_name"));
		}
		if (isColumnExists(rs, "l_name")) {
			obj.setLastName(rs.getString("l_name"));
		}
		if (isColumnExists(rs, "gender")) {
			obj.setGender(rs.getString("gender"));
		}
		if (isColumnExists(rs, "salutation")) {
			obj.setSalutation(rs.getString("salutation"));
		}
		if (isColumnExists(rs, "birth_year")) {
			obj.setBirthYear(rs.getLong("birth_year"));
		}
		if (isColumnExists(rs, "job_title")) {
			obj.setJobTitle(rs.getString("job_title"));
		}
		if (isColumnExists(rs, "country")) {
			obj.setCountry(rs.getString("country"));
		}
		if (isColumnExists(rs, "role")) {
			obj.setRole(ERole.valueOf(rs.getString("role")));
		}
		return obj;
	}

}
