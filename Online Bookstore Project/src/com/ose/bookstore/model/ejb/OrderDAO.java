/**
 * 
 */
package com.ose.bookstore.model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ose.bookstore.model.entity.OrderDetail;

/**
 * EJB session bean that handles the business logic part of the orderDetails table
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
//No-interface view, Stateless Bean
@Stateless
@LocalBean
public class OrderDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**Adds a new entry if a new order is confirmed
	 * @param order 
	 */
	public void create(OrderDetail order){
		entityManager.merge(order);
	}
	
	
}
