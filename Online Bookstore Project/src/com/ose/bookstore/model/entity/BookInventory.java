package com.ose.bookstore.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BookInventory
 * 
 */
@Entity
public class BookInventory implements Serializable {

	private static final long serialVersionUID = 1L;

	//Fields
	@Id
	@GeneratedValue
	private int inventoryId;
	
	private int bookId;
	
	private int booksInStock;
	
	//Getters and Setters
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBooksInStock() {
		return booksInStock;
	}
	public void setBooksInStock(int booksInStock) {
		this.booksInStock = booksInStock;
	}

}
