package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper extends ValueMapperBase<Count> {

	@Override
	public Count mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Count(rs.getInt("total"));
	}

}
