package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvgVolatilityValue {

	private Integer period;
	private NewsVolatilityValues value;

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public NewsVolatilityValues getValue() {
		return value;
	}

	public void setValue(NewsVolatilityValues value) {
		this.value = value;
	}

}
