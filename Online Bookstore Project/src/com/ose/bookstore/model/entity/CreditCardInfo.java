package com.ose.bookstore.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

	@Embedded
	private Address address;

	// Setters and Getters
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
