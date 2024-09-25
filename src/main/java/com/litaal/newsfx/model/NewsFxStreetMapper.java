package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsFxStreetMapper extends ValueMapperBase<NewsFxStreet> {

	@Override
	public NewsFxStreet mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsFxStreet obj = new NewsFxStreet();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getString("id"));
		}
		if (isColumnExists(rs, "event_id")) {
			obj.setEventId(rs.getString("event_id"));
		}
		if (isColumnExists(rs, "date_utc")) {
			obj.setDateUtc(rs.getString("date_utc"));
		}
		if (isColumnExists(rs, "date")) {
			obj.setDate(rs.getLong("date"));
		}
		if (isColumnExists(rs, "period_date_utc")) {
			obj.setPeriodDateUtc(rs.getString("period_date_utc"));
		}
		if (isColumnExists(rs, "period_date")) {
			obj.setPeriodDate(rs.getLong("period_date"));
		}
		if (isColumnExists(rs, "period_type")) {
			obj.setPeriodType(rs.getString("period_type"));
		}
		if (isColumnExists(rs, "actual")) {
			obj.setActual(rs.getDouble("actual"));
		}
		if (isColumnExists(rs, "revise")) {
			obj.setRevise(rs.getDouble("revise"));
		}
		if (isColumnExists(rs, "consensus")) {
			obj.setConsensus(rs.getDouble("consensus"));
		}
		if (isColumnExists(rs, "ratio_deviation")) {
			obj.setRatioDeviation(rs.getDouble("ratio_deviation"));
		}
		if (isColumnExists(rs, "previous")) {
			obj.setPrevious(rs.getDouble("previous"));
		}
		if (isColumnExists(rs, "is_better_than_expected")) {
			obj.setIsBetterThanExpected(rs.getBoolean("is_better_than_expected"));
		}
		if (isColumnExists(rs, "name")) {
			obj.setName(rs.getString("name"));
		}
		if (isColumnExists(rs, "country_code")) {
			obj.setCountryCode(rs.getString("country_code"));
		}
		if (isColumnExists(rs, "currency_code")) {
			obj.setCurrencyCode(rs.getString("currency_code"));
		}
		if (isColumnExists(rs, "unit")) {
			obj.setUnit(rs.getString("unit"));
		}
		if (isColumnExists(rs, "potency")) {
			obj.setPotency(rs.getString("potency"));
		}
		if (isColumnExists(rs, "volatility")) {
			obj.setVolatility(rs.getString("volatility"));
		}
		if (isColumnExists(rs, "is_allday")) {
			obj.setIsAllDay(rs.getBoolean("is_allday"));
		}
		if (isColumnExists(rs, "is_tentative")) {
			obj.setIsTentative(rs.getBoolean("is_tentative"));
		}
		if (isColumnExists(rs, "is_preliminary")) {
			obj.setIsPreliminary(rs.getBoolean("is_preliminary"));
		}
		if (isColumnExists(rs, "is_report")) {
			obj.setIsReport(rs.getBoolean("is_report"));
		}
		if (isColumnExists(rs, "is_speech")) {
			obj.setIsSpeech(rs.getBoolean("is_speech"));
		}
		if (isColumnExists(rs, "last_updated")) {
			obj.setLastUpdated(rs.getLong("last_updated"));
		}
		return obj;
	}

}
