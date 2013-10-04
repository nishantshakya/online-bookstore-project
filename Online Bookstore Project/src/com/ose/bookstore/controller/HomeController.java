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

	private List<Books> bookList;

	private String searchCategory;
	
	@SuppressWarnings("unused")
	private List<Books> filterBookList;
	
	public String goToSearch() {
		System.out.println(searchCategory);
		return "searchedResults";
	}

	// Getters and Setters
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
		return bookListDao.getBookList(); 
	}

	/**Gets bookList according to the search category
	 * @return specified book list
	 */
	public List<Books> getFilterBookList(){
		if (searchCategory.equals("Title")) {
			bookList = bookListDao.search(searchString, "title");
		} else if (searchCategory.equals("Author")) {
			bookList = bookListDao.search(searchString, "author");
		}
		else{
			bookList = bookListDao.search(searchString, "all");
		}
		return bookList;
	}
	
	public void setBookList(List<Books> bookList) {
		this.bookList = getBookList();
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public void setFilterBookList(List<Books> filterBookList) {
		this.filterBookList = filterBookList;
	}

}
