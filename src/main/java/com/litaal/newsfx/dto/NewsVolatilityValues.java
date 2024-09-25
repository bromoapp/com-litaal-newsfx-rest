package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsVolatilityValues {

	private String uuid;
	private String provider;
	private Long time;
	private Double m1;
	private Double m5;
	private Double m15;
	private Double m30;
	private Double h1;

	public NewsVolatilityValues() {
		super();
		this.m1 = 0.0;
		this.m5 = 0.0;
		this.m15 = 0.0;
		this.m30 = 0.0;
		this.h1 = 0.0;
	}

	public NewsVolatilityValues(Long time) {
		super();
		this.time = time;
		this.m1 = 0.0;
		this.m5 = 0.0;
		this.m15 = 0.0;
		this.m30 = 0.0;
		this.h1 = 0.0;
	}

	public NewsVolatilityValues(String uuid, String provider, Long time) {
		super();
		this.uuid = uuid;
		this.provider = provider;
		this.time = time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
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
