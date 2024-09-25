package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PairTickMapper extends ValueMapperBase<PairTick> {

	@Override
	public PairTick mapRow(ResultSet rs, int rowNum) throws SQLException {
		PairTick obj = new PairTick();
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "time")) {
			obj.setTime(rs.getLong("time"));
		}
		if (isColumnExists(rs, "total_ticks")) {
			obj.setTotalTicks(rs.getInt("total_ticks"));
		}
		if (isColumnExists(rs, "highest_ask")) {
			obj.setHighestAsk(rs.getDouble("highest_ask"));
		}
		if (isColumnExists(rs, "lowest_bid")) {
			obj.setLowestBid(rs.getDouble("lowest_bid"));
		}
		if (isColumnExists(rs, "spread")) {
			obj.setSpread(rs.getDouble("spread"));
		}
		if (isColumnExists(rs, "ask_vol")) {
			obj.setAskVol(rs.getDouble("ask_vol"));
		}
		if (isColumnExists(rs, "bid_vol")) {
			obj.setBidVol(rs.getDouble("bid_vol"));
		}
		if (isColumnExists(rs, "total_vol")) {
			obj.setTotalVol(rs.getInt("total_vol"));
		}
		return obj;
	}

}
