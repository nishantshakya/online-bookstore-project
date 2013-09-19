package com.ose.bookstore.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entity implementation class for Entity: UserDetails
 *
 */
@Entity

public class UserDetails implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//Fields
	@Id
	@GeneratedValue
	private int userId;
	
	private int loginId;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Embedded
	private Address address;

	
	// Setters and Getters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
