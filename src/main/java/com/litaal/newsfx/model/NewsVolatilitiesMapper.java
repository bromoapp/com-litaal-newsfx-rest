package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsVolatilitiesMapper extends ValueMapperBase<NewsVolatilities> {

	@Override
	public NewsVolatilities mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsVolatilities obj = new NewsVolatilities();
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "m1_time")) {
			obj.setM1Time(rs.getLong("m1_time"));
		}
		if (isColumnExists(rs, "m5_time")) {
			obj.setM5Time(rs.getLong("m5_time"));
		}
		if (isColumnExists(rs, "m15_time")) {
			obj.setM15Time(rs.getLong("m15_time"));
		}
		if (isColumnExists(rs, "m30_time")) {
			obj.setM30Time(rs.getLong("m30_time"));
		}
		if (isColumnExists(rs, "h1_time")) {
			obj.setH1Time(rs.getLong("h1_time"));
		}
		if (isColumnExists(rs, "uuids")) {
			obj.setUuids(rs.getString("uuids"));
		}
		if (isColumnExists(rs, "m1_gap")) {
			obj.setM1Gap(rs.getDouble("m1_gap"));
		}
		if (isColumnExists(rs, "m5_gap")) {
			obj.setM5Gap(rs.getDouble("m5_gap"));
		}
		if (isColumnExists(rs, "m15_gap")) {
			obj.setM15Gap(rs.getDouble("m15_gap"));
		}
		if (isColumnExists(rs, "m30_gap")) {
			obj.setM30Gap(rs.getDouble("m30_gap"));
		}
		if (isColumnExists(rs, "h1_gap")) {
			obj.setH1Gap(rs.getDouble("h1_gap"));
		}
		if (isColumnExists(rs, "m1_avg_gap")) {
			obj.setM1AvgGap(rs.getDouble("m1_avg_gap"));
		}
		if (isColumnExists(rs, "m5_avg_gap")) {
			obj.setM5AvgGap(rs.getDouble("m5_avg_gap"));
		}
		if (isColumnExists(rs, "m15_avg_gap")) {
			obj.setM15AvgGap(rs.getDouble("m15_avg_gap"));
		}
		if (isColumnExists(rs, "m30_avg_gap")) {
			obj.setM30AvgGap(rs.getDouble("m30_avg_gap"));
		}
		if (isColumnExists(rs, "h1_avg_gap")) {
			obj.setH1AvgGap(rs.getDouble("h1_avg_gap"));
		}
		return obj;
	}

}
