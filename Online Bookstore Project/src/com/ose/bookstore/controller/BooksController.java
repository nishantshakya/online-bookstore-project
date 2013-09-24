/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RateEvent;

import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.ejb.RatingsDao;
import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.Ratings;
import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * Deals with all the page links dispatches present in browsebooks page and
 * bookprofile page
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

	@EJB
	BookListDao bookListDao;

	@EJB
	RatingsDao ratingsDao;

	@Inject
	ShoppingCart shoppingCart;

	@Inject
	Books currentBook;

	@Inject
	Ratings ratings;
	//
	private static final long serialVersionUID = 1L;

	private Integer rating;// Average User rating of a book

	private int newRating;
	
	private int currentId;

	// Constructor
	public BooksController() {
	}

	// public void getId(Books books) {
	//
	// }

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
	 * 
	 * @return shoppingCart page showing the user's Shopping Cart
	 */
	public String addToShoppingCart() {
		shoppingCart.setBookId(currentBook.getBookId());
		shoppingCartDao.add(shoppingCart);
		return "shoppingCart";
	}

	/**
	 * Sets the selected book as the current book
	 * 
	 * @param books
	 *            selected book from the jsf page
	 * @return bookProfile the selected book profile page
	 */
	public String showBookDetails(Books books) {
		this.currentBook = books;
		return "bookProfile";
	}

	//
	public Integer getRating() {
		// System.out.println("You Rated: " + rating);
		return rating;

	}

	public void setRating(Integer rating) {
		this.rating = rating;
		// System.out.println("You Rated re:" + rating);
		// sysout
	}

	 public void onrate(RateEvent rateEvent) {
	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
	 "Thank you for your review:", "");
	
	 FacesContext.getCurrentInstance().addMessage(null, message);
	 
	 System.out.println("You've rated: " + newRating);
	 ratings = ratingsDao.getUserRating(currentId, 1).get(0);
	 ratings.setUserRating5(newRating);
//	 System.out.println(ratings.getBookId());
	 ratingsDao.setUserRating(ratings);
	 }

	public void init() {
		currentBook = bookListDao.getBook(currentId);
		rating = ratingsDao.bookRating(currentId);
		 ratings = ratingsDao.getUserRating(currentId, 1).get(0);
		newRating = ratings.getUserRating5();
		if (!ratingsDao.getUserRating(currentId, 1).isEmpty()) {
			ratings.setUserRating5(ratingsDao.getUserRating(currentId, 1)
					.get(0).getUserRating5());
		}
		System.out.println(ratingsDao.getUserRating(currentId, 1)
					.get(0).getUserRating5());
		System.out.println(ratings.getUserRating5());
		// sysout
		System.out.println("HAHAHA: " + currentBook.getBookId());
		System.out.println(rating);
	}

	// public void selectListener(ActionEvent event)
	// throws AbortProcessingException {
	// System.out.println("Hello there!!");
	// UICommand link = (UICommand) event.getComponent();
	// UIParameter param = (UIParameter) link.getChildren();
	// currentId = (int) param.getValue();
	// // System.out.println("The ID try" + currentID);
	// }

	// Getters and Setters
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

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

	//
	public Ratings getRatings() {
		return ratings;
	}

	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}

	public int getNewRating() {
		return newRating;
	}

	public void setNewRating(int newRating) {
		this.newRating = newRating;
	}

}
