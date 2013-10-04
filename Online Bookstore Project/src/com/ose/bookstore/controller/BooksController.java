/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.ose.bookstore.model.ejb.TempShoppingCartDAO;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.Ratings;
import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * Deals with all the page links dispatches present in browsebooks page and
 * bookprofile page
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Named
@RequestScoped
public class BooksController implements Serializable {

	@EJB
	ShoppingCartDAO shoppingCartDao;

	@EJB
	BookListDAO bookListDao;

	@EJB
	RatingsDAO ratingsDao;

	@Inject
	TempShoppingCartDAO tempShoppingCartDao;

	@Inject
	ShoppingCart shoppingCart;

	@Inject
	UserAccountController userAccountController;

	@Inject
	Books currentBook;
	
	@Inject
	Ratings ratings;

	List<ShoppingCart> currentCart;
	
	private static final long serialVersionUID = 1L;

	private Integer rating;/*Average User rating of a book*/

	private int newRating;/*New rating by a user*/

	private int currentId;/*Current Book Id*/

	private int ratingsCount;/*Number of Ratings for a book by different users*/
	
	private Map<Integer, Integer> bookNumber; /*for dropdown menu in bookProfile page*/

	public BooksController() {
	
	}

	/**
	 * Sets the book to be added to the shopping cart in the shoppingCart
	 * instance;
	 *  calls the cartDao function for adding the book for registered users
	 * 	calls tempcartDao function for appending books for unregistered users
	 * 
	 * @return 
	 */
	public String addToShoppingCart() {
		
		if (userAccountController.isFlag()) { 
			shoppingCart.setBookId(currentBook.getBookId());
			shoppingCart.setUserId(userAccountController.getUserDetails()
					.getUserId());
			shoppingCartDao.add(shoppingCart);
		}
		else{
			shoppingCart.setBookId(currentBook.getBookId());
			tempShoppingCartDao.add(shoppingCart);
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item(s) added to Cart:", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "";
	}

	/**
	 * Sets the selected book as the current book
	 * 
	 * @param books selected book from the jsf page
	 * @return bookProfile the selected book profile page
	 */
	public String showBookDetails(Books books) {
		this.currentBook = books;
		return "bookProfile?faces-redirect=true";
	}

	/**Sets new Rating of a book for a user
	 * @param rateEvent
	 */
	public void onrate(RateEvent rateEvent) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,	"Thank you for your review:", "");
		FacesContext.getCurrentInstance().addMessage(null, message);

		if (!ratingsDao.getUserRating(currentId, userAccountController.getUserDetails().getUserId()).isEmpty()) {
			ratings = ratingsDao.getUserRating(currentId, userAccountController.getUserDetails().getUserId()).get(0);
			ratings.setBookId(currentId);
			ratings.setUserRating5(newRating);
			ratingsDao.setUserRating(ratings);
		} else {
			ratings.setRatingsId(0);
			ratings.setBookId(currentId);
			ratings.setUserRating5(newRating);
			ratings.setUserId(userAccountController.getUserDetails().getUserId());
			ratingsDao.setUserRating(ratings);
		}
	}

	/**The pre-Render function that is loaded before rendering bookProfile page
	 * 
	 */
	public void init() {
		currentBook = bookListDao.getBook(currentId);
		rating = ratingsDao.bookRating(currentId);
		ratingsCount = ratingsDao.getBookRating(currentId).size();
		if (!ratingsDao.getUserRating(currentId, userAccountController.getUserDetails().getUserId()).isEmpty()) {
			ratings = ratingsDao.getUserRating(currentId, userAccountController.getUserDetails().getUserId()).get(0);
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

	public List<ShoppingCart> getCurrentCart() {
		if (userAccountController.isFlag()){
			currentCart = tempShoppingCartDao.getCart();
		}
		return currentCart;
	}

	public void setCurrentCart(List<ShoppingCart> currentCart) {
		this.currentCart = currentCart;
	}

	public Map<Integer, Integer> getBookNo() {
		bookNumber = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= 10; i++)
			bookNumber.put(i, i); 
		return bookNumber;
	}
}
