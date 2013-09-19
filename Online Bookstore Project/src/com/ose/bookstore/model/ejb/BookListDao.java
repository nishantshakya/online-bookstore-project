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
 * EJB session bean that handles the business logic part of the user details table
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
//No-interface view, Stateless Bean
@Stateless
@LocalBean
public class BookListDao {
	
	@PersistenceContext//Makes the session bean thread-safe, no need of using transaction handling
	private EntityManager entityManager; //EntityManager is used to persist entities to a DB
	
	public BookListDao(){
		
	}
	
	/**Searches the table for matching string
	 * @param searchString the string to be searched for
	 * @return the List containing the Books with matching searchstring
	 */
	public List<Books> search(String searchString){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Books> query = cb.createQuery(Books.class);
		Root<Books> c = query.from(Books.class);
		
		List<Predicate> plist = new ArrayList<Predicate>(); 
		plist.add(cb.like(c.<String> get("title"), "%" + searchString + "%"));
		query.select(c);
		query.where(cb.or(plist.toArray(new Predicate[plist.size()])));//The predicates are conjuncted using or
			
		return entityManager.createQuery(query).getResultList();	
	}
	
	/**Lists all the books present in the table
	 * @return the List containing all the entries in the table
	 */
	public List<Books> getBookList() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //invokes the getCriteriaBuilder() method on the entity manager
		CriteriaQuery<Books> query = cb.createQuery(Books.class); //obtains the instance of Contacts class implementing CriteriaQuery interface
		Root<Books> c = query.from(Books.class);//Jpa entity to be querying from (equivalent to 'from' in SQL)
		query.select(c);
		query.orderBy(cb.asc(c.get("bookId")));

		return entityManager.createQuery(query).getResultList();
	}
	
	/**A selected by primary key; bookId
	 * @param id the unique id to a book
	 * @return the selected book
	 */
	public Books getBook(int id){
		Books book = new Books();
		book = entityManager.find(Books.class, id);
		return book;
	}
	

}
