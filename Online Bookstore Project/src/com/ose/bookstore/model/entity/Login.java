package com.ose.bookstore.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Login
 *
 */
@Entity

public class Login implements Serializable {

	
	private static final long serialVersionUID = 1L;

   
	@Id @GeneratedValue
	private int loginId;
	
	@Column(unique = true)
	private String userEmail;
	
//	@Column
	private String password;
	
	private String secPassword;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecPassword() {
		return secPassword;
	}

	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}
	
	
}
