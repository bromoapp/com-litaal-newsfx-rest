package com.litaal.newsfx.model;

public class Credential {
	public static String TBLNAME = "credential";

	private String uuid;
	private String username;
	private String password;
	private Boolean enabled;

	public Credential() {
		super();
	}

	public Credential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
