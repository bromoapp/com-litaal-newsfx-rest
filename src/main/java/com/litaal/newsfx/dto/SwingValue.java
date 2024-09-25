package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwingValue {

	private Double m1;
	private Double m5;
	private Double m15;
	private Double m30;
	private Double h1;

	public SwingValue() {
		super();
	}

	public SwingValue(Double m1, Double m5, Double m15, Double m30, Double h1) {
		super();
		this.m1 = m1;
		this.m5 = m5;
		this.m15 = m15;
		this.m30 = m30;
		this.h1 = h1;
	}

	public Double getM1() {
		return m1;
	}

	public void setM1(Double m1) {
		this.m1 = m1;
	}

	public Double getM5() {
		return m5;
	}

	public void setM5(Double m5) {
		this.m5 = m5;
	}

	public Double getM15() {
		return m15;
	}

	public void setM15(Double m15) {
		this.m15 = m15;
	}

	public Double getM30() {
		return m30;
	}

	public void setM30(Double m30) {
		this.m30 = m30;
	}

	public Double getH1() {
		return h1;
	}

	public void setH1(Double h1) {
		this.h1 = h1;
	}

}
