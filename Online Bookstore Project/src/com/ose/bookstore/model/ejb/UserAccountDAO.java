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
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
//No-interface view, Stateless Bea
@Stateless
@LocalBean
public class UserAccountDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**Creates new user entry if no matching email id in the table
	 * @param userDetails the new user to be persisted
	 * @return
	 */
	public boolean create(UserDetails userDetails) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> query = cb.createQuery(UserDetails.class);
		Root<UserDetails> c = query.from(UserDetails.class);
		query.select(c).where(cb.and(cb.equal(c.get("userEmail"),userDetails.getUserEmail())));
		if (entityManager.createQuery(query).getResultList().isEmpty()) {
			entityManager.persist(userDetails);
			return true;
		}
		else{
			return false;
		}
	}

	/** Selects user by primary key
	 * @param userId
	 * @return
	 */
	public UserDetails getUser(int userId) {
		UserDetails ud = entityManager.find(UserDetails.class, userId);
		return ud;
	}

	/**Selects the userDetails from table of matching user
	 * @param user
	 * @return
	 */
	public List<UserDetails> getCurrentUser(UserDetails user) {

		String userEmail = user.getUserEmail();
		String password = user.getPassword();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDetails> query = cb.createQuery(UserDetails.class);
		Root<UserDetails> c = query.from(UserDetails.class);
		query.select(c).where(cb.and(cb.equal(c.get("userEmail"), userEmail),cb.equal(c.get("password"), password)));
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * @param user
	 */
	public void editUser(UserDetails user) {
		entityManager.merge(user);
	}

}
