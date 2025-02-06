package com.JavaTech.Email;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.JavaTech.ExceptionHandle.EmailSendException;
import com.JavaTech.Utils.ExceptionMessage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailDetailServiceImpl implements EmailDetailService{

	 @Autowired 
	 private JavaMailSender javaMailSender;

	 @Value("${spring.mail.username}") 
	 private String sender;
	
	@Override
	public String sendSimpleMail(EmailDetail details) {		 
	     SimpleMailMessage mailMessage= new SimpleMailMessage();
    	 mailMessage.setFrom(sender);
         mailMessage.setTo(details.getRecipient());
         mailMessage.setText(details.getMsgBody());
         mailMessage.setSubject(details.getSubject());
         javaMailSender.send(mailMessage);            
	     return ExceptionMessage.EMAIL_SUCCESS_MSG;
	}

	@Override
	public String sendMailWithAttachment(EmailDetail details) throws MessagingException, FileNotFoundException {				
		 MimeMessage mimeMessage= javaMailSender.createMimeMessage();
         MimeMessageHelper mimeMessageHelper;
         FileSystemResource file= new FileSystemResource(new File(details.getAttachment()));
         mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
         mimeMessageHelper.addAttachment(file.getFilename(), file);        	                         	         
         mimeMessageHelper.setFrom(sender);
         mimeMessageHelper.setTo(details.getRecipient());
         mimeMessageHelper.setText(details.getMsgBody());
         mimeMessageHelper.setSubject(details.getSubject());
         javaMailSender.send(mimeMessage);
    	 return ExceptionMessage.EMAIL_SUCCESS_MSG;                                       
	  }

}
