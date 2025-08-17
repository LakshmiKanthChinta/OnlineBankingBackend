package com.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

	@Value("${spring.mail.username}") // from application.properties
	private String from;
	
	@Autowired
	private JavaMailSender javaMailSender;  // interface from JavaMailSender dependency 
	
	public void sendMailInTheFormatOfText(String customerEmailId, String subject, String text){
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		// customer Mail_Id
		simpleMailMessage.setTo(customerEmailId);
		
		// Application connected Mail_Id
		simpleMailMessage.setFrom(from);
		
		// Welcome to bank
		simpleMailMessage.setSubject(subject);
		
		// Registration success or any other message
		simpleMailMessage.setText(text);
		
		javaMailSender.send(simpleMailMessage);
	}
}
