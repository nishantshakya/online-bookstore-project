package com.ose.bookstore.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
//import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: CreditCardInfor
 * 
 */
@Entity
public class CreditCardInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum CreditCardType {
		VISA, MASTER_CARD, AMERICAN_EXPRESS;
	}

	// Fields
	@Id
	@GeneratedValue
	private int cardId;

	@Enumerated(EnumType.STRING)
	private CreditCardType creditCardType;

	@Column(length = 12)
	private String expirationDate;

	@Column(length = 30)
	private String firstName;
	
	@Column(length = 30)
	private String middleName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 20)
	private String phone;
	
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

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
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

//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

}
