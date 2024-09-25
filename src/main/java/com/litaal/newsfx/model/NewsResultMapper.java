package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.litaal.newsfx.constant.EReality;

public class NewsResultMapper extends ValueMapperBase<NewsResult> {

	@Override
	public NewsResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsResult obj = new NewsResult();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "avp")) {
			obj.setAvp(EReality.valueOf(rs.getString("avp")));
		}
		if (isColumnExists(rs, "avp_diff")) {
			obj.setAvpDiff(rs.getDouble("avp_diff"));
		}
		if (isColumnExists(rs, "avf")) {
			if (null != rs.getString("avf")) {
				obj.setAvf(EReality.valueOf(rs.getString("avf")));
				if (isColumnExists(rs, "avf_diff")) {
					obj.setAvfDiff(rs.getDouble("avf_diff"));
				}
			}
		}
		if (isColumnExists(rs, "fvp")) {
			if (null != rs.getString("fvp")) {
				obj.setFvp(EReality.valueOf(rs.getString("fvp")));
				if (isColumnExists(rs, "fvp_diff")) {
					obj.setFvpDiff(rs.getDouble("fvp_diff"));
				}
			}
		}
		if (isColumnExists(rs, "effect")) {
			obj.setEffect(rs.getInt("effect"));
		}
		return obj;
	}

}
