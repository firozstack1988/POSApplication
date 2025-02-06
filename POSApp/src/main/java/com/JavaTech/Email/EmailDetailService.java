package com.JavaTech.Email;

import java.io.FileNotFoundException;

import jakarta.mail.MessagingException;

public interface EmailDetailService {

	    String sendSimpleMail(EmailDetail details);
	    
	    String sendMailWithAttachment(EmailDetail details) throws MessagingException, FileNotFoundException;
}
