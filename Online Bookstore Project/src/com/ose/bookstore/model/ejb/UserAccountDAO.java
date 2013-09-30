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
public class UserAccountDAO {

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

	public UserDetails getUser(int userId) {
		UserDetails ud = entityManager.find(UserDetails.class, userId);
		return ud;
	}

	public List<UserDetails> getCurrentUser(UserDetails user) {
		
		String userEmail = user.getUserEmail();
		String password = user.getPassword();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> query = cb.createQuery(UserDetails.class);
		Root<UserDetails> c = query.from(UserDetails.class);
		query.select(c).where(cb.and(cb.equal(c.get("userEmail"),userEmail),cb.equal(
				c.get("password"),password)));
		return entityManager.createQuery(query).getResultList();
		
	}

	/**
	 * Updates the userDetails table
	 * 
	 * @param ud
	 *            the userDetails to be edited
	 */
	public void editUser(UserDetails user) {
		entityManager.merge(user);
	}

}
