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

import com.ose.bookstore.model.ejb.BookListDAO;
import com.ose.bookstore.model.ejb.RatingsDAO;
import com.ose.bookstore.model.ejb.ShoppingCartDAO;
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
	ShoppingCartDAO shoppingCartDao;

	@EJB
	BookListDAO bookListDao;

	@EJB
	RatingsDAO ratingsDao;

	@Inject
	ShoppingCart shoppingCart;

	@Inject
	UserAccountController userAccountController;

	@Inject
	Books currentBook;

	@Inject
	Ratings ratings;
	//
	private static final long serialVersionUID = 1L;

	private Integer rating;// Average User rating of a book

	private int newRating;

	private int currentId;

	private int ratingsCount;

	// Constructor
	public BooksController() {
	}

	/**
	 * For dropdown menu in bookProfile page; gives the maximum number of books
	 * that can be added to the cart at a time
	 * 
	 */
	private Map<Integer, Integer> bookNumber;

	public Map<Integer, Integer> getBookNo() {
		bookNumber = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= 10; i++)
			bookNumber.put(i, i); // label, value
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
		shoppingCart.setUserId(userAccountController.getUserDetails()
				.getUserId());
		shoppingCartDao.add(shoppingCart);
		
		return "shoppingCart?faces-redirect=true";
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
		return "bookProfile?faces-redirect=true";
	}

	public void onrate(RateEvent rateEvent) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Thank you for your review:", "");

		FacesContext.getCurrentInstance().addMessage(null, message);

		System.out.println("You've rated: " + newRating);

		if (!ratingsDao.getUserRating(currentId,
				userAccountController.getUserDetails().getUserId()).isEmpty()) {
			ratings = ratingsDao.getUserRating(currentId,
					userAccountController.getUserDetails().getUserId()).get(0);
			ratings.setBookId(currentId);
			ratings.setUserRating5(newRating);

			System.out.println("Previous Ratings");
			ratingsDao.setUserRating(ratings);
		} else {
			System.out.println("New Ratings");
			System.out.println(ratings.getRatingsId());
			ratings.setRatingsId(0);
			ratings.setBookId(currentId);
			ratings.setUserRating5(newRating);
			ratings.setUserId(userAccountController.getUserDetails()
					.getUserId());
			ratingsDao.setUserRating(ratings);
		}
	}

	public void init() {
		currentBook = bookListDao.getBook(currentId);
		rating = ratingsDao.bookRating(currentId);
		ratingsCount = ratingsDao.getBookRating(currentId).size();
		if (!ratingsDao.getUserRating(currentId,
				userAccountController.getUserDetails().getUserId()).isEmpty()) {
			ratings = ratingsDao.getUserRating(currentId,
					userAccountController.getUserDetails().getUserId()).get(0);
			newRating = ratings.getUserRating5();
		}
	}

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

	public Integer getRating() {
		return rating;

	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

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

	public int getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

}
