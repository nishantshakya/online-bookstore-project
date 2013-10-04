/**
 * 
 */
package com.ose.bookstore.model.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ose.bookstore.model.entity.Books;


/**
 * EJB session bean that handles the business logic part of the user details
 * table
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */

/*No-interface view, Stateless Bean*/

@Stateless
@LocalBean
public class BookListDAO {

	@PersistenceContext 
	private EntityManager entityManager; 

	public BookListDAO() {

	}

	/** Searches the table for matching string
	 * @param searchString the string to be searched for
	 * @param category column to be searched for
	 * @return filtered search list
	 */
	public List<Books> search(String searchString, String category) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Books> query = cb.createQuery(Books.class);
		Root<Books> c = query.from(Books.class);

		query.select(c);
		if (category.equals("all")) {
			List<Predicate> plist = new ArrayList<Predicate>(); 
			plist.add(cb.like(c.<String> get("author"), "%" + searchString + "%"));
			plist.add(cb.like(c.<String> get("title"), "%" + searchString + "%"));
		
			query.select(c);
			query.where(cb.or(plist.toArray(new Predicate[plist.size()])));
		} else {
			query.where(cb.like(c.<String> get(category), "%" + searchString + "%"));
		}
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Lists all the books present in the table
	 * 
	 * @return the List containing all the entries in the table
	 */
	public List<Books> getBookList() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); 
		CriteriaQuery<Books> query = cb.createQuery(Books.class); 
		Root<Books> c = query.from(Books.class);
		query.select(c);

		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * A selected by primary key; bookId
	 * 
	 * @param id the unique id to a book
	 * @return the selected book
	 */
	public Books getBook(int id) {
		Books book = new Books();
		book = entityManager.find(Books.class, id);
		return book;
	}

}
