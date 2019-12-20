package com.coursecube.spring.serviceimpl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.coursecube.spring.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	JavaMailSender mailSender;
	
	public void sendMail(String from,String to,String subject,String body) {
		try {
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper mailMsg=new MimeMessageHelper(mimeMessage);
			mailMsg.setFrom(from);
			mailMsg.setTo(to);
			mailMsg.setSubject(subject);
			mailMsg.setText(body,true);
			System.out.println("MailserviceImpl");
			mailSender.send(mimeMessage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void sendMail(String from,String to,String subject,String body,File file) {
		try {
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper mailMsg=new MimeMessageHelper(mimeMessage);
			mailMsg.setFrom(from);
			mailMsg.setTo(to);
			mailMsg.setSubject(subject);
			mailMsg.setText(body,true);
			FileSystemResource fileRes=new FileSystemResource(file);
			mailMsg.addAttachment(file.getName(),fileRes);
			mailSender.send(mimeMessage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
}