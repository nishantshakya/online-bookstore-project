/**
 * 
 */
package com.ose.bookstore.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.CountryListDao;
import com.ose.bookstore.model.ejb.LoginDao;
import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.Login;
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
	
	@EJB
	LoginDao loginDao;

	@EJB
	CountryListDao countryListDao;
	
	@Inject
	UserDetails userDetails;
	
	@Inject
	Login login;
	
	private String country;
	
	private Map<String,String> countryValue;
//	@Inject
//	Address address;

	/**The selected user's detail is edited
	 * @return null returns to same page
	 */
	public String editDetails() {
		System.out.println("Current user: " + userDetails.getFirstName());
		userAccountDao.editUser(userDetails);
		return "null";
	}

	/**The cart is loaded to the order table
	 * @return forwards to orderBooks page
	 */
	public String toOrderPage() {
		System.out.println("Here");
		this.userDetails = userAccountDao.getUser(2);
		System.out.println("user id : " + userDetails.getUserId());
		return "orderBooks";
	}

	/**Retrieves the user's details from the database
	 * @return forwards to editDetails page
	 */
	public String showDetails() {
//		System.out.println("ok");
		this.userDetails = userAccountDao.getUser(2);
//		System.out.println("error");
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
		loginDao.createAccount(login);
		System.out.println(login.getUserEmail());
		System.out.println(login.getPassword());
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
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}


}
