package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMql5Mapper extends ValueMapperBase<NewsMql5> {

	@Override
	public NewsMql5 mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsMql5 obj = new NewsMql5();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "event_type")) {
			obj.setEventType(rs.getInt("event_type"));
		}
		if (isColumnExists(rs, "time_mode")) {
			obj.setTimeMode(rs.getInt("time_mode"));
		}
		if (isColumnExists(rs, "processed")) {
			obj.setProcessed(rs.getInt("processed"));
		}
		if (isColumnExists(rs, "url")) {
			obj.setUrl(rs.getString("url"));
		}
		if (isColumnExists(rs, "event_name")) {
			obj.setEventName(rs.getString("event_name"));
		}
		if (isColumnExists(rs, "importance")) {
			obj.setImportance(rs.getString("importance"));
		}
		if (isColumnExists(rs, "currency_code")) {
			obj.setCurrencyCode(rs.getString("currency_code"));
		}
		if (isColumnExists(rs, "forecast_value")) {
			obj.setForecastValue(rs.getString("forecast_value"));
		}
		if (isColumnExists(rs, "previous_value")) {
			obj.setPreviousValue(rs.getString("previous_value"));
		}
		if (isColumnExists(rs, "old_previous_value")) {
			obj.setOldPreviousValue(rs.getString("old_previous_value"));
		}
		if (isColumnExists(rs, "actual_value")) {
			obj.setActualValue(rs.getString("actual_value"));
		}
		if (isColumnExists(rs, "release_date")) {
			obj.setReleaseDate(rs.getLong("release_date"));
		}
		if (isColumnExists(rs, "impact_direction")) {
			obj.setImpactDirection(rs.getInt("impact_direction"));
		}
		if (isColumnExists(rs, "impact_value")) {
			obj.setImpactValue(rs.getString("impact_value"));
		}
		if (isColumnExists(rs, "impact_value_f")) {
			obj.setImpactValueF(rs.getString("impact_value_f"));
		}
		if (isColumnExists(rs, "country")) {
			obj.setCountry(rs.getString("country"));
		}
		if (isColumnExists(rs, "country_name")) {
			obj.setCountryName(rs.getString("country_name"));
		}
		if (isColumnExists(rs, "full_date")) {
			obj.setFullDate(rs.getString("full_date"));
		}
		return obj;
	}

}
