package com.mk.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendMail(String to, String subject, String body) {
		boolean isMailSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(body,true);

			mailSender.send(mimeMessage);
			isMailSent = true;
		} //try
		catch (Exception e) {
			e.printStackTrace();
		} //catch
		return isMailSent;
	}//mail

}//class