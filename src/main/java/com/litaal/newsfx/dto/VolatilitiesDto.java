package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VolatilitiesDto {

	private NewsVolatilityValues main;
	private AvgVolatilityValue average;
	private Object[] times;
	private Object[] m1Values;
	private Object[] m5Values;
	private Object[] m15Values;
	private Object[] m30Values;
	private Object[] h1Values;

	public VolatilitiesDto() {
		super();
	}

	public NewsVolatilityValues getMain() {
		return main;
	}

	public void setMain(NewsVolatilityValues main) {
		this.main = main;
	}

	public AvgVolatilityValue getAverage() {
		return average;
	}

	public void setAverage(AvgVolatilityValue average) {
		this.average = average;
	}

	public Object[] getTimes() {
		return times;
	}

	public void setTimes(Object[] times) {
		this.times = times;
	}

	public Object[] getM1Values() {
		return m1Values;
	}

	public void setM1Values(Object[] m1Values) {
		this.m1Values = m1Values;
	}

	public Object[] getM5Values() {
		return m5Values;
	}

	public void setM5Values(Object[] m5Values) {
		this.m5Values = m5Values;
	}

	public Object[] getM15Values() {
		return m15Values;
	}

	public void setM15Values(Object[] m15Values) {
		this.m15Values = m15Values;
	}

	public Object[] getM30Values() {
		return m30Values;
	}

	public void setM30Values(Object[] m30Values) {
		this.m30Values = m30Values;
	}

	public Object[] getH1Values() {
		return h1Values;
	}

	public void setH1Values(Object[] h1Values) {
		this.h1Values = h1Values;
	}

}
