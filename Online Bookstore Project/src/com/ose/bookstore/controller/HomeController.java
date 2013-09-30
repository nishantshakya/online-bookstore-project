/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.BookListDAO;
import com.ose.bookstore.model.entity.Books;
//import com.ose.bookstore.model.ejb.RatingsDao;

/**
 * Deals with the page links dispatches present in the home page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@SessionScoped
public class HomeController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	BookListDAO bookListDao;

	@Inject
	Books books;
	
	private String searchString;
	
	@SuppressWarnings("unused")
	private List<Books> bookList;

	/**
	 * Lists the books according to the searchString
	 * 
	 * @return list of books as per the searchString
	 */
	public List<Books> bookList() {
		if (searchString != null) {
			return bookListDao.search(searchString); // result of search
		}
		return bookListDao.getBookList(); // all books
	}

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

}
