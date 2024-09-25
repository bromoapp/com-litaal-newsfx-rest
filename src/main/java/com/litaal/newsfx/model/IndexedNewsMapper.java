package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.litaal.newsfx.constant.ECurrency;

public class IndexedNewsMapper extends ValueMapperBase<IndexedNews> {

	@Override
	public IndexedNews mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndexedNews obj = new IndexedNews();
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "title")) {
			obj.setTitle(rs.getString("title"));
		}
		if (isColumnExists(rs, "country")) {
			obj.setCountry(rs.getString("country"));
		}
		if (isColumnExists(rs, "currency")) {
			obj.setCurrency(ECurrency.valueOf(rs.getString("currency")));
		}
		if (isColumnExists(rs, "impact")) {
			obj.setImpact(rs.getInt("impact"));
		}
		if (isColumnExists(rs, "provider")) {
			obj.setProvider(rs.getString("provider"));
		}
		return obj;
	}

}
