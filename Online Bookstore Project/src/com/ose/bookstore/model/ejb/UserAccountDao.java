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

import com.ose.bookstore.model.entity.UserDetails;

/**
 * EJB session bean that handles the business logic part of the user details
 * table
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
// No-interface view, Stateless Bean
@Stateless
@LocalBean
public class UserAccountDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Selects the user according to the primary key, userId
	 * 
	 * @param userId
	 *            unique Id of the user
	 * @return ud the selected user's details
	 */

	public void create(UserDetails userDetails) {
		entityManager.persist(userDetails);
	}

	// public int getUserId()
	public UserDetails getUser(int userId) {
		UserDetails ud = entityManager.find(UserDetails.class, userId);
		return ud;
	}

	public UserDetails getCurrentUser(UserDetails user) {
		String userEmail = user.getUserEmail();
		String password = user.getPassword();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> query = cb.createQuery(UserDetails.class);
		Root<UserDetails> c = query.from(UserDetails.class);
		query.select(c).where(cb.and(cb.equal(c.get("userEmail"),userEmail),cb.equal(
				c.get("password"),password)));
		
//		UserDetails currentUser = entityManager.find(UserDetails.class, user.getUserId());
//		if(currentUser.getUserEmail().equalsIgnoreCase(userEmail) && currentUser.getPassword().equals(password)){
//			return currentUser;
//		}
//		return null;
		if (entityManager.createQuery(query).getResultList().isEmpty()){
			return null;
		}
		return entityManager.createQuery(query).getResultList().get(0);
		
	}

	// public
	/**
	 * Updates the userDetails table
	 * 
	 * @param ud
	 *            the userDetails to be edited
	 */
	public void editUser(UserDetails user) {
		// UserDetails user = getUser(id);
		entityManager.merge(user);

	}

}
