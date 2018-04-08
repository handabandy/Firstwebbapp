package com.springwebapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	@Value("${spring.mail.webaddress}")
	private String WEBADDRESS;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMessage(String email, String firstname, String activationKey) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres regisztrálás");
			message.setText("Kedves " + firstname + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!"
					+"\n \n Íme az aktivációs link: "+WEBADDRESS+activationKey);
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba e-mail küldéskor az alábbi címre: " + email + "  " + e);
		}
		

	}
	
	
}
