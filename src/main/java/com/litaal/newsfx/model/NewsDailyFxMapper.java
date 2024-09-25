package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDailyFxMapper extends ValueMapperBase<NewsDailyFx> {

	@Override
	public NewsDailyFx mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsDailyFx obj = new NewsDailyFx();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "ticker")) {
			obj.setTicker(rs.getString("ticker"));
		}
		if (isColumnExists(rs, "symbol")) {
			obj.setSymbol(rs.getString("symbol"));
		}
		if (isColumnExists(rs, "date")) {
			obj.setDate(rs.getLong("date"));
		}
		if (isColumnExists(rs, "title")) {
			obj.setTitle(rs.getString("title"));
		}
		if (isColumnExists(rs, "description")) {
			obj.setDescription(rs.getString("description"));
		}
		if (isColumnExists(rs, "importance")) {
			obj.setImportance(rs.getString("importance"));
		}
		if (isColumnExists(rs, "previous")) {
			obj.setPrevious(rs.getString("previous"));
		}
		if (isColumnExists(rs, "previous_val")) {
			obj.setPreviousVal(rs.getDouble("previous_val"));
		}
		if (isColumnExists(rs, "forecast")) {
			obj.setForecast(rs.getString("forecast"));
		}
		if (isColumnExists(rs, "forecast_val")) {
			obj.setForecastVal(rs.getDouble("forecast_val"));
		}
		if (isColumnExists(rs, "country")) {
			obj.setCountry(rs.getString("country"));
		}
		if (isColumnExists(rs, "actual")) {
			obj.setActual(rs.getString("actual"));
		}
		if (isColumnExists(rs, "actual_val")) {
			obj.setActualVal(rs.getDouble("actual_val"));
		}
		if (isColumnExists(rs, "all_day_event")) {
			obj.setAllDayEvent(rs.getBoolean("all_day_event"));
		}
		if (isColumnExists(rs, "currency")) {
			obj.setCurrency(rs.getString("currency"));
		}
		if (isColumnExists(rs, "reference")) {
			obj.setReference(rs.getString("reference"));
		}
		if (isColumnExists(rs, "revised")) {
			obj.setRevised(rs.getString("revised"));
		}
		if (isColumnExists(rs, "mean_actual")) {
			obj.setMeanActual(rs.getString("mean_actual"));
		}
		if (isColumnExists(rs, "mean_previous")) {
			obj.setMeanPrevious(rs.getString("mean_previous"));
		}
		if (isColumnExists(rs, "last_update")) {
			obj.setLastUpdate(rs.getString("last_update"));
		}
		return obj;
	}

}
