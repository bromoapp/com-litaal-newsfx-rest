package com.litaal.newsfx.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.dto.EmailMessage;

@Component
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private Environment env;

	public EmailServiceImpl(@Autowired EventBus eventBus) {
		super();
		eventBus.register(this);
	}

	@Subscribe
	@Override
	public void onMessage(EmailMessage message) throws Exception {
		Properties prop = System.getProperties();
		prop.put(Property.MailConfig.SMTP_HOST, env.getProperty(Property.MailConfig.SMTP_HOST));
		prop.put(Property.MailConfig.SMTP_PORT, env.getProperty(Property.MailConfig.SMTP_PORT));
		prop.put(Property.MailConfig.SMTP_SSL_ENABLE, env.getProperty(Property.MailConfig.SMTP_SSL_ENABLE));
		prop.put(Property.MailConfig.SMTP_AUTH_ENABLE, env.getProperty(Property.MailConfig.SMTP_AUTH_ENABLE));

		Session sess = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(env.getProperty(Property.MailConfig.ADMIN_USERNAME),
						env.getProperty(Property.MailConfig.ADMIN_PASSWORD));
			}
		});
		sess.setDebug(true);
		MimeMessage msg = new MimeMessage(sess);
		msg.setFrom(message.getSender());
		msg.addRecipient(RecipientType.TO, new InternetAddress(message.getRecipient()));
		msg.setSubject(message.getSubject());
		msg.setContent(message.getContent(), "text/html");

		Transport.send(msg);
	}

}
