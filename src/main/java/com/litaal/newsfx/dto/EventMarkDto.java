package com.litaal.newsfx.dto;

public class EventMarkDto {

	private double position;
	private long time;

	public EventMarkDto() {
		super();
	}

	public EventMarkDto(double position, long time) {
		super();
		this.position = position;
		this.time = time;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
