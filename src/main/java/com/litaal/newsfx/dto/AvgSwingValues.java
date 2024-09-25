package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvgSwingValues {

	private Integer period;
	private SwingValue swingUp;
	private SwingValue swingDown;

	public AvgSwingValues() {
		super();
	}

	public AvgSwingValues(Integer period, SwingValue swingUp, SwingValue swingDown) {
		super();
		this.period = period;
		this.swingUp = swingUp;
		this.swingDown = swingDown;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public SwingValue getSwingUp() {
		return swingUp;
	}

	public void setSwingUp(SwingValue swingUp) {
		this.swingUp = swingUp;
	}

	public SwingValue getSwingDown() {
		return swingDown;
	}

	public void setSwingDown(SwingValue swingDown) {
		this.swingDown = swingDown;
	}

}
