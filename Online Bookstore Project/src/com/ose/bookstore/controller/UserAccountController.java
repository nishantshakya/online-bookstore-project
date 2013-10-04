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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.CountryListDAO;
import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.UserDetails;


/**
 * Deals with all the page links dispatches present in <b><u>editDetails</u></b> page and the
 * pages involving with <b><u>editDetails</u></b> data
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Named
@SessionScoped
public class UserAccountController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	UserAccountDAO userAccountDao;

	@EJB
	CountryListDAO countryListDao;

	@Inject
	UserDetails userDetails;

	private boolean flag = false; /*Login status flag; true if logged in*/

	private Map<String, String> countryValue;/*for dropdown menu*/

	@SuppressWarnings("unused")
	private Long memberAge;

	/**
	 * The selected user's detail is edited
	 * 
	 * @return null returns to same page
	 */
	public String editDetails() {
		System.out.println("Current user: " + userDetails.getFirstName());
		userAccountDao.editUser(userDetails);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Edited Successfully"));
		return "";
	}

	/**
	 * @return forwards to <b><u>orderBooks</u></b> page
	 */
	public String toOrderPage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Register/Login Account to buy books", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "registered/orderBooks?faces-redirect=true";
	}

	/**
	 * @return forwards to editDetails page
	 */
	public String showDetails() {
		return "registered/editDetails?faces-redirect=true";
	}

	/**Registers User by persisting in database
	 * @return
	 */
	public String registerUser() {
		Date currentDate = new Date();
		userDetails.setDate(currentDate);
		if(userAccountDao.create(userDetails)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your account has been created"));
			return "/webpages/home?faces-redirect=true";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Existing UserEmail"));
			return null;
		}
	}

	/**Sets flag true if correct login info
	 * @return
	 */
	public String checkLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (userDetails.getPassword().isEmpty()	|| userDetails.getUserEmail().isEmpty()) {
			context.addMessage(null, new FacesMessage("UserEmail/Password is empty"));
			flag = false;
		}else if (userAccountDao.getCurrentUser(userDetails).isEmpty()) {
			context.addMessage(null, new FacesMessage("Login failed: Email address or Password is incorrect"));
			flag = false;
		} else {
			userDetails = userAccountDao.getCurrentUser(userDetails).get(0);
			// userSession.setUser(userDetails);
			// Cookie loginCookie = new Cookie("user",
			// userDetails.getFirstName());
			// loginCookie.setMaxAge(30*60);
			flag = true;
		}
		
		return null;
	}

	/**
	 * Logs the current user out by invalidating the session.
	 * 
	 * @return page {@literal faces-config.xml} to redirect back to the
	 *         {@literal home.xhtml} page.
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		// Cookie loginCookie = null;
		// loginCookie.setMaxAge(0);
		flag = false;
		return "/webpages/home?faces-redirect=true";
	}

	public String signUp() {
		return "/webpages/userRegistration?faces-redirect=true";
	}

	// Getters and Setters
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Map<String, String> getSelectCountryValue() {
		countryValue = new LinkedHashMap<String, String>();
		for (int i = 0; i < countryListDao.getCountry().size(); i++) {
			countryValue.put(countryListDao.getCountry().get(i).getName(),
					countryListDao.getCountry().get(i).getName());
		}
		return countryValue;
	}

	/**Calculates current user's membership age
	 * @return membership age in days
	 */
	public Long getMemberAge() {
		Date currentDate = new Date();
		long diff = currentDate.getTime() - userDetails.getDate().getTime();
		return (diff / (1000 * 60 * 60 * 24));
	}

	public void setMemberAge(Long memberAge) {
		this.memberAge = memberAge;
	}

}
