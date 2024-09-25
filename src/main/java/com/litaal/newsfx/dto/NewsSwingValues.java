package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsSwingValues {

	private String uuid;
	private String provider;
	private Long time;
	private SwingValue swingUp;
	private SwingValue swingDown;

	public NewsSwingValues() {
		super();
	}

	public NewsSwingValues(String uuid, String provider, Long time) {
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

	public SwingValue getSwingUp() {
		if (swingUp == null) {
			swingUp = new SwingValue();
		}
		return swingUp;
	}

	public void setSwingUp(SwingValue swingUp) {
		this.swingUp = swingUp;
	}

	public SwingValue getSwingDown() {
		if (swingDown == null) {
			swingDown = new SwingValue();
		}
		return swingDown;
	}

	public void setSwingDown(SwingValue swingDown) {
		this.swingDown = swingDown;
	}

}
