package com.litaal.newsfx.dto;

import com.litaal.newsfx.constant.EResponseType;

public class ResponseDto {

	private EResponseType type;
	private String message;

	public ResponseDto() {
		super();
	}

	public ResponseDto(EResponseType type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public EResponseType getType() {
		return type;
	}

	public void setType(EResponseType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
