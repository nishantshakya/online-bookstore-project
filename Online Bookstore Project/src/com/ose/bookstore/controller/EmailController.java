/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * Handles sending of mail 
 * 
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Named
@SessionScoped
public class EmailController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private String password;

	/**Navigates to page2 after successful sending of mail
	 * @return page to be navigated to
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public String goToNextPage() throws MessagingException,
			UnsupportedEncodingException {
		// email = "shakya.nishanta@gmail.com";
		sendMessage(email);
		return "page2";
	}

	@Resource(name = "mail/shakya.nishanta@gmail.com")
	private Session mailSession;

	/**Sends the mail with email body to the specified email address
	 * @param email the recipient email address
	 */
	public void sendMessage(String email) {
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setSubject("[app] Email Alert");
			msg.setSentDate(new Date());
			msg.setFrom();
			msg.setRecipient(RecipientType.TO, new InternetAddress(email));
			msg.setText("Hello " + email);
			Transport.send(msg);
		} catch (MessagingException me) {
		}
	}

	//Getters and Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
