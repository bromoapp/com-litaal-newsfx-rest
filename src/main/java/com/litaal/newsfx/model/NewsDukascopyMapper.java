package com.litaal.newsfx.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDukascopyMapper extends ValueMapperBase<NewsDukascopy> {

	@Override
	public NewsDukascopy mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsDukascopy obj = new NewsDukascopy();
		if (isColumnExists(rs, "uuid")) {
			obj.setUuid(rs.getString("uuid"));
		}
		if (isColumnExists(rs, "date")) {
			obj.setDate(rs.getLong("date"));
		}
		if (isColumnExists(rs, "id")) {
			obj.setId(rs.getLong("id"));
		}
		if (isColumnExists(rs, "country")) {
			obj.setCountry(rs.getString("country"));
		}
		if (isColumnExists(rs, "currency")) {
			obj.setCurrency(rs.getString("currency"));
		}
		if (isColumnExists(rs, "title")) {
			obj.setTitle(rs.getString("title"));
		}
		if (isColumnExists(rs, "periodicity")) {
			obj.setPeriodicity(rs.getString("periodicity"));
		}
		if (isColumnExists(rs, "show_description")) {
			obj.setShowDescription(rs.getBoolean("show_description"));
		}
		if (isColumnExists(rs, "description")) {
			obj.setDescription(rs.getString("description"));
		}
		if (isColumnExists(rs, "impact")) {
			obj.setImpact(rs.getInt("impact"));
		}
		if (isColumnExists(rs, "actual")) {
			obj.setActual(rs.getString("actual"));
		}
		if (isColumnExists(rs, "actual_norm")) {
			obj.setActualNorm(rs.getString("actual_norm"));
		}
		if (isColumnExists(rs, "forecast")) {
			obj.setForecast(rs.getString("forecast"));
		}
		if (isColumnExists(rs, "forecast_norm")) {
			obj.setForecastNorm(rs.getString("forecast_norm"));
		}
		if (isColumnExists(rs, "previous")) {
			obj.setPrevious(rs.getString("previous"));
		}
		if (isColumnExists(rs, "previous_norm")) {
			obj.setPreviousNorm(rs.getString("previous_norm"));
		}
		if (isColumnExists(rs, "value_order")) {
			obj.setValueOrder(rs.getString("value_order"));
		}
		if (isColumnExists(rs, "value_format")) {
			obj.setValueFormat(rs.getString("value_format"));
		}
		if (isColumnExists(rs, "tag")) {
			obj.setTag(rs.getString("tag"));
		}
		if (isColumnExists(rs, "historical_count")) {
			obj.setHistoricalCount(rs.getInt("historical_count"));
		}
		if (isColumnExists(rs, "effect")) {
			obj.setEffect(rs.getInt("effect"));
		}
		if (isColumnExists(rs, "dd_source")) {
			obj.setDdSource(rs.getString("dd_source"));
		}
		if (isColumnExists(rs, "dd_measures")) {
			obj.setDdMeasures(rs.getString("dd_measures"));
		}
		if (isColumnExists(rs, "dd_usual_effect")) {
			obj.setDdUsualEffect(rs.getString("dd_usual_effect"));
		}
		if (isColumnExists(rs, "dd_frequency")) {
			obj.setDdFrequency(rs.getString("dd_frequency"));
		}
		if (isColumnExists(rs, "dd_next_release")) {
			obj.setDdNextRelease(rs.getString("dd_next_release"));
		}
		if (isColumnExists(rs, "dd_derived_via")) {
			obj.setDdDerivedVia(rs.getString("dd_derived_via"));
		}
		if (isColumnExists(rs, "dd_acro")) {
			obj.setDdAcro(rs.getString("dd_acro"));
		}
		return obj;
	}

}
