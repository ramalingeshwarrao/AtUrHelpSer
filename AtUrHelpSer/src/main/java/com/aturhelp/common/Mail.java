package com.aturhelp.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.aturhelp.constants.Constants;

public class Mail implements Runnable{
	
	final static Logger LOG = Logger.getLogger(Mail.class);
	
	private String toEmail;
	private String subject;
	private String body;
	
	public Mail(String toEmail, String subject, String body) {
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
	}
	
	public void run() {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Constants.EMAIL, Constants.EMAIL_PASSWORD);
					}
				  });
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(Constants.EMAIL));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			msg.setSubject(subject);
			msg.setText(body);
			
			Transport.send(msg);
			
		} catch (Exception e) {
			LOG.error("Fail to send mail", e);
		}
	}

}
