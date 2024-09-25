package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenDto extends ResponseDto {

	private String token;
	private String expired;

	public AccessTokenDto() {
		super();
	}

	public AccessTokenDto(String token, String expired) {
		super();
		this.token = token;
		this.expired = expired;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

}
