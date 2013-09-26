/**
 * 
 */
package com.ose.bookstore.model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ose.bookstore.model.entity.UserDetails;

/**
 * EJB session bean that handles the business logic part of the user details table
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
//No-interface view, Stateless Bean
@Stateless
@LocalBean
public class UserAccountDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**Selects the user according to the primary key, userId
	 * @param userId unique Id of the user
	 * @return ud the selected user's details
	 */
	
	public void create(UserDetails userDetails){
		entityManager.persist(userDetails);
	}
	
	public UserDetails getUser(int userId){
		UserDetails ud = entityManager.find(UserDetails.class, userId);
		return ud;
	}
	
	/**Updates the userDetails table
	 * @param ud the userDetails to be edited
	 */
	public void editUser(UserDetails user){
//		UserDetails user = getUser(id);
		entityManager.merge(user);
		
	}
}
