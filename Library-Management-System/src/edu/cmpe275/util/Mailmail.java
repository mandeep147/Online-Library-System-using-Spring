package edu.cmpe275.util;

import java.util.Properties;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


//@Service
public class Mailmail {

	//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");
	//Mailmail mail = context.getBean(Mailmail.class);
	private MailSender mailSender;// = context.getBean(MailSender.class);
	
	public Mailmail() {
		this.mailSender = new JavaMailSenderImpl();
		((JavaMailSenderImpl) this.mailSender).setHost("smtp.gmail.com");
		((JavaMailSenderImpl) this.mailSender).setUsername("librarymanagement275@gmail.com");
		((JavaMailSenderImpl) this.mailSender).setPassword("Test@123");
		Properties prop = new Properties();
		prop.setProperty("mail.smpt.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.port", "465");
		((JavaMailSenderImpl) this.mailSender).setJavaMailProperties(prop);
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
}