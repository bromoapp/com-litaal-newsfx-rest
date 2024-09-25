package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class ValueMapperBase<T> implements RowMapper<T> {

	protected boolean isColumnExists(ResultSet rs, String name) {
		boolean status = false;
		try {
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 1; i < meta.getColumnCount() + 1; i++) {
				if (meta.getColumnName(i).equalsIgnoreCase(name)) {
					status = true;
				}
			}
		} catch (SQLException e) {
			// Ignore
		}
		return status;
	}

}
