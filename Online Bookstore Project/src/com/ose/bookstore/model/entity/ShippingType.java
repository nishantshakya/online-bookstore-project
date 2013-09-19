package com.ose.bookstore.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: ShippingType
 * 
 */
@Entity
public class ShippingType implements Serializable {

	private static final long serialVersionUID = 1L;


	public enum ShipingType{
		STANDARD, EXPRESS;
	}
	@Id
	@GeneratedValue
	private int shippingId;
	
	@Enumerated(EnumType.STRING)
	private ShipingType type;
	
	private Double rate;
	
	//Getters and Setters
	public int getShippingId() {
		return shippingId;
	}
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	public ShipingType getType() {
		return type;
	}
	public void setType(ShipingType type) {
		this.type = type;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}

	

}
