package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwingsDto {

	private NewsSwingValues main;
	private AvgSwingValues average;
	private Object[] times;

	public SwingsDto() {
		super();
	}

	public SwingsDto(NewsSwingValues main, AvgSwingValues average) {
		super();
		this.main = main;
		this.average = average;
	}

	public NewsSwingValues getMain() {
		return main;
	}

	public void setMain(NewsSwingValues main) {
		this.main = main;
	}

	public AvgSwingValues getAverage() {
		return average;
	}

	public void setAverage(AvgSwingValues average) {
		this.average = average;
	}

	public Object[] getTimes() {
		return times;
	}

	public void setTimes(Object[] times) {
		this.times = times;
	}

}
