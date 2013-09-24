/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.ejb.RatingsDao;
import com.ose.bookstore.model.entity.Books;

/**
 * Deals with the page links dispatches present in the home page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class HomeController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	BookListDao bookListDao;

	@EJB
	RatingsDao ratingsDao;
	
	@Inject
	Books books;

	private String searchString;
	
	private int id;
	
	@SuppressWarnings("unused")
	private List<Books> bookList;

//
//	public String convertToCurrency(double price) {
//		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//		System.out.println(currencyFormatter.format(price));
//		return currencyFormatter.format(price);
//	}

	/**
	 * Lists the books according to the searchString
	 * 
	 * @return list of books as per the searchString
	 */
	public List<Books> bookList() {
		if (searchString != null) {
			return bookListDao.search(searchString); // result of search
		}

//		System.out.println(ratingsDao.getRating(1).get(0).getUserRating5());
		return bookListDao.getBookList(); // all books
	}

/*	public void init() {
		if (id == 0) {
			String message = "Bad request. Please use a link from within the system.";
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									message, null));
			return;
		}
		books = bookListDao.getBook(id);

		if (books == null) {
			String message = "Bad request. Unknown user.";
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									message, null));
		}

	}

	public void loadBook() {
		System.out.println("book iD: " + id);
		this.books = bookListDao.getBook(id);
		// return null;
	}*/
/*	public String showBookDetails(Books book) {
		this.books = book;
		System.out.println(book.getAuthor());
//		this.books = bookListDao.getBook(bid);
//		System.out.println(books.getAuthor());
		return "bookProfile";
	}*/
	
	//Getters and Setters
	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<Books> getBookList() {
		return bookList();
	}

	public void setBookList(List<Books> bookList) {
		this.bookList = bookList();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
