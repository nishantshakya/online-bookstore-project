/**
 * 
 */
package com.ose.bookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author nishant
 * 
 */
@Embeddable
public class Address {
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	public Address() {
	}

	@Column(length = 30)
	private String firstName;
	
	@Column(length = 30)
	private String middleName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 10)
	private String buildingNo;
	
	@Column(length = 20)
	private String phone;
	
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

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
