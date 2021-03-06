package com.ose.bookstore.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entity implementation class for Entity: UserDetails
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Entity

public class UserDetails implements Serializable {

	
	private static final long serialVersionUID = 1L;

	//Fields
	@Id
	@GeneratedValue
	private int userId;
	@Column(unique = true)
	private String userEmail;
	
//	@Column
	private String password;
	
	private String secPassword;
	
	@Column(length = 30)
	private String firstName;
	
	@Column(length = 30)
	private String middleName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 20)
	private String phone;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
//	@Embedded
//	private Address address;
	@Column(length = 10)
	private String buildingNo;

	@Column(length = 10)
	private String streetNo;

	@Column(length = 50)
	private String streetName;

	@Column(length = 50)
	private String city;

	@Column(length = 10)
	private String zip;

	@Column(length = 30)
	private String state;

	@Column(length = 40)
	private String country;
	
	
	// Setters and Getters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
