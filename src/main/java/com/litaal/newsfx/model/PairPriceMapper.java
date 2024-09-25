package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PairPriceMapper extends ValueMapperBase<PairPrice> {

	@Override
	public PairPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
		PairPrice obj = new PairPrice();
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "time")) {
			obj.setTime(rs.getLong("time"));
		}
		if (isColumnExists(rs, "open")) {
			obj.setOpen(rs.getDouble("open"));
		}
		if (isColumnExists(rs, "high")) {
			obj.setHigh(rs.getDouble("high"));
		}
		if (isColumnExists(rs, "low")) {
			obj.setLow(rs.getDouble("low"));
		}
		if (isColumnExists(rs, "close")) {
			obj.setClose(rs.getDouble("close"));
		}
		if (isColumnExists(rs, "volume")) {
			obj.setVolume(rs.getDouble("volume"));
		}
		if (isColumnExists(rs, "pip_gap")) {
			obj.setPipGap(rs.getDouble("pip_gap"));
		}
		if (isColumnExists(rs, "swing_up")) {
			obj.setSwingUp(rs.getDouble("swing_up"));
		}
		if (isColumnExists(rs, "swing_down")) {
			obj.setSwingDown(rs.getDouble("swing_down"));
		}
		return obj;
	}

}
