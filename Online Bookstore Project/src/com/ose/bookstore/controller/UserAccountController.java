/**
 * 
 */
package com.ose.bookstore.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * Deals with all the page links dispatches present in editDetails page and the
 * pages involving with userDetails data
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class UserAccountController {

	@EJB
	UserAccountDao userAccountDao;

	@Inject
	UserDetails userDetails;

	/**The selected user's detail is edited
	 * @return null returns to same page
	 */
	public String editDetails() {
		userAccountDao.editUser(userDetails);
		return "null";
	}

	/**The cart is loaded to the order table
	 * @return forwards to orderBooks page
	 */
	public String toOrderPage() {
		this.userDetails = userAccountDao.getUser(1);
		return "orderBooks";
	}

	/**Retrieves the user's details from the database
	 * @return forwards to editDetails page
	 */
	public String showDetails() {
		this.userDetails = userAccountDao.getUser(1);
		return "editDetails";
	}
	
	//Getters and Setters
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}


}
