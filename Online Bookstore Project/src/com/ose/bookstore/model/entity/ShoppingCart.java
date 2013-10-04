package com.ose.bookstore.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: ShoppingCart
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Entity
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Fields
	@Id
	@GeneratedValue
	private int scId;

	// @ManyToOne
	// private Books books;
	private int userId;

	private int bookId;

	private int bookQuantity;

	// Getters and Setters
	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
}
