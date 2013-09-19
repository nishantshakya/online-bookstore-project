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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String editDetails() {
		userAccountDao.editUser(userDetails);
		return "null";
	}

	public String toOrderPage() {
		this.userDetails = userAccountDao.getUser(1);
		return "orderBooks";
	}

	public String showDetails() {
		this.userDetails = userAccountDao.getUser(1);
		return "editDetails";
	}

}
