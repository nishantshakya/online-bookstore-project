/**
 * 
 */
package com.ose.bookstore.dto;

import java.io.Serializable;


/**
 * Optimised class maded by joining ShoppingCart and Books
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// properties
	private int bookId;

	private int cartId;
	
	private String coverPage;

	private String author;

	private String title;

	private String edition;

	private double price;

	private float discount;

	private int quantity;

	private double totalPrice;

	// Constructor
	public Cart(int bookId, String author, String title, String edition,
			double price, float discount, int quantity, double totalPrice,
			String coverPage, int cartId) {
		super();
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.edition = edition;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.coverPage = coverPage;
		this.cartId = cartId;
	}

	// Getters and Setters
	public String getCoverPage() {
		return coverPage;
	}

	public void setCoverPage(String coverPage) {
		this.coverPage = coverPage;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

}
