package com.pro.controller;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.pro.pojo.Account;
import com.pro.pojo.UserInfo;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Component
public class rejectMail {
		public static void sendMail(Account a,UserInfo u) {
			
			final String fromEmail="keshavpatlawdiya@gmail.com";
			final String pass="zmvzlqxsibqjtqlc";
			
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			
			
			Session session=Session.getInstance(props, new Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, pass);
				}
			});
			
			try {
				Message message=new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmail));
				
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
				
				
				message.setSubject("Account Alert Info");
				String msg = "<p>Hi <strong>" + a.getAccname() + " Your Account Open request Rejected</strong>,</p>"+
				
				"Acc Type  :"+a.getAcctype() +
				"<br>Acc No = "+a.getAccno()+
				"<br>User Id :  = "+u.getEmail();
				
				message.setContent(msg, "text/html");
				Transport.send(message);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}