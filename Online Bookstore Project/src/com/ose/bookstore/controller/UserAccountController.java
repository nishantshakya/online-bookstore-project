/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.CountryListDAO;
import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.UserDetails;
//import org.eclipse.persistence.sessions.Login;

/**
 * Deals with all the page links dispatches present in editDetails page and the
 * pages involving with userDetails data
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@SessionScoped
public class UserAccountController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UserAccountDAO userAccountDao;

	@EJB
	CountryListDAO countryListDao;
	
	@Inject
	UserDetails userDetails;

	private String country;
	
	private Map<String,String> countryValue;

	/**The selected user's detail is edited
	 * @return null returns to same page
	 */
	public String editDetails(UserDetails userDetails) {
		System.out.println("Current user: " + userDetails.getFirstName());
		userAccountDao.editUser(userDetails);
		return "null";
	}

	/**The cart is loaded to the order table
	 * @return forwards to orderBooks page
	 */
	public String toOrderPage() {
//		System.out.println("Here");
//		this.userDetails = userAccountDao.getUser(2);
//		System.out.println("user id : " + userDetails.getUserId());
		return "orderBooks?faces-redirect=true";
	}

	/**Retrieves the user's details from the database
	 * @return forwards to editDetails page
	 */
	public String showDetails() {
//		System.out.println("ok");
//		this.userDetails = userAccountDao.getUser(1);
//		System.out.println("error");
//		System.out.println("user id : "  + userDetails.getUserId());
		return "editDetails";
	}
	
	
	
	public Map<String,String> getSelectCountryValue()
	{
		countryValue = new LinkedHashMap<String, String>();
		for (int i = 0; i < countryListDao.getCountry().size(); i++) {
			countryValue.put(countryListDao.getCountry().get(i).getName(),countryListDao.getCountry().get(i).getName());
		}	
		return countryValue;
	}
	
	public String registerUser(){
	
		Date currentDate = new Date();
		userDetails.setDate(currentDate);
		userAccountDao.create(userDetails);
		return "home";
	}
	
	//Getters and Setters

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		System.out.println(country);
	}
	
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
