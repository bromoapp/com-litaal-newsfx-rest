package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NavLinkMapper extends ValueMapperBase<NavLink> {

	@Override
	public NavLink mapRow(ResultSet rs, int rowNum) throws SQLException {
		NavLink obj = new NavLink();
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "title")) {
			obj.setTitle(rs.getString("title"));
		}
		if (isColumnExists(rs, "icon")) {
			obj.setIcon(rs.getString("icon"));
		}
		if (isColumnExists(rs, "link")) {
			obj.setLink(rs.getString("link"));
		}
		if (isColumnExists(rs, "roles")) {
			obj.setRoles(rs.getString("roles"));
		}
		return obj;
	}

}
