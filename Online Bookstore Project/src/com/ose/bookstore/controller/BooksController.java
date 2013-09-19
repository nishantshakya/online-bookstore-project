/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * Deals with all the page links dispatches present in browsebooks page and bookprofile page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class BooksController implements Serializable {

	/**
	 * 
	 */
	@EJB
	ShoppingCartDao shoppingCartDao;

	@Inject
	ShoppingCart shoppingCart;
	
	@Inject
	Books currentBook;

	private static final long serialVersionUID = 1L;

	//Constructor
	public BooksController() {
	}

//	public void getId(Books books) {
//
//	}

	/**
	 * For dropdown menu in bookProfile page; gives the maximum number of books
	 * that can be added to the cart at a time
	 * 
	 */
	private static Map<Integer, Object> bookNumber;

	static {
		bookNumber = new LinkedHashMap<Integer, Object>();
		// bookNo.put(1,1);
		for (int i = 1; i <= 10; i++)
			bookNumber.put(i, i);

	}

	public Map<Integer, Object> getBookNo() {
		return bookNumber;
	}

	/**
	 * Sets the book to be added to the shopping cart in the shoppingCart
	 * instance and calls the Dao function for adding the book
	 * @return shoppingCart page showing the user's Shopping Cart
	 */
	public String addToShoppingCart() {
		shoppingCart.setBookId(currentBook.getBookId());
		shoppingCartDao.add(shoppingCart);
		return "shoppingCart";
	}

	
	/**Sets the selected book as the current book
	 * @param books selected book from the jsf page
	 * @return bookProfile the selected book profile page
	 */
	public String showBookDetails(Books books) {
		this.currentBook = books;
		return "bookProfile";
	}

	//Getters and Setters
	public Books getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(Books currentBook) {
		this.currentBook = currentBook;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
