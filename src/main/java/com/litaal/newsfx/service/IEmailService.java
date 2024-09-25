package com.litaal.newsfx.service;

import com.litaal.newsfx.dto.EmailMessage;

public interface IEmailService {

	public void onMessage(EmailMessage message) throws Exception;

}
