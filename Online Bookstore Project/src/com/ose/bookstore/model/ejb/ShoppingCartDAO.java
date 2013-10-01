/**
 * 
 */
package com.ose.bookstore.model.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * EJB session bean that handles the business logic part of the shoppingCart table
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
//No-interface view, Stateless Bean
@Stateless
@LocalBean
public class ShoppingCartDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**Adds or updates the shoppingCart table by comparing the bookId 
	 * @param shoppingCart the entry to be added/updated
	 */
	public void add(ShoppingCart shoppingCart) {
		List<ShoppingCart> sCart = getCart(shoppingCart.getUserId());//sCart contains the List of all the entries in the shoppingCart table
	
		//Checks the table for existing books in the table; bookQuantity to be updated accordingly
		for (int i = 0; i < sCart.size(); i++) {
			if (sCart.get(i).getBookId() == shoppingCart.getBookId() && sCart.get(i).getUserId() == shoppingCart.getUserId()){
				shoppingCart.setBookQuantity(shoppingCart.getBookQuantity()+ sCart.get(i).getBookQuantity());
				shoppingCart.setScId(sCart.get(i).getScId());
			}
		}
		entityManager.merge(shoppingCart);
	}

	
	/**Gets the cart of a selected user
	 * @param userID unique ID of the user
	 * @return the List containing the entries of the selected User
	 */
	public List<ShoppingCart> getCart(int userID) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //invokes the getCriteriaBuilder() method on the entity manager
		CriteriaQuery<ShoppingCart> query = cb.createQuery(ShoppingCart.class); //obtains the instance of Contacts class implementing CriteriaQuery interface
		Root<ShoppingCart> c = query.from(ShoppingCart.class);//Jpa entity to be querying from (equivalent to 'from' in SQL)
		query.select(c).where(cb.equal(c.get("userId"),userID));
		return entityManager.createQuery(query).getResultList();
	}
	
	public void deleteEntry(ShoppingCart sc){
		entityManager.remove(entityManager.merge(sc));
	}
}
