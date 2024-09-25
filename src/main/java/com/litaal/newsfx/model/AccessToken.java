package com.litaal.newsfx.model;

public class AccessToken {
	public static String TBLNAME = "access_token";

	private String token;
	private long expired;

	public AccessToken(String token, long expired) {
		super();
		this.token = token;
		this.expired = expired;
	}

	public AccessToken() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpired() {
		return expired;
	}

	public void setExpired(long expired) {
		this.expired = expired;
	}

}
