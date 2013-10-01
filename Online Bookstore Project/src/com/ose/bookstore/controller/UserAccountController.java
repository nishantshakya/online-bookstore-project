/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	// Integer currentId;

	private boolean flag = false;

	private Map<String, String> countryValue;

	private Long memberAge;

//	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
//			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
//			+ "(\\.[A-Za-z]{2,})$";
//
//	private Pattern pattern;
//
//	private Matcher matcher;

	/**
	 * The selected user's detail is edited
	 * 
	 * @return null returns to same page
	 */
	public String editDetails() {
		System.out.println("Current user: " + userDetails.getFirstName());
		userAccountDao.editUser(userDetails);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Edited Successfully"));
		return "null";
	}

	/**
	 * The cart is loaded to the order table
	 * 
	 * @return forwards to orderBooks page
	 */
	public String toOrderPage() {
		return "registered/orderBooks?faces-redirect=true";
	}

	/**
	 * Retrieves the user's details from the database
	 * 
	 * @return forwards to editDetails page
	 */
	public String showDetails() {
		return "registered/editDetails?faces-redirect=true";
	}

	public String registerUser() {
		Date currentDate = new Date();
		userDetails.setDate(currentDate);
		userAccountDao.create(userDetails);
		return "/webpages/home?faces-redirect=true";
	}

	public String checkLogin() {
		System.out.println("asdfsad");
		FacesContext context = FacesContext.getCurrentInstance();
		// if (userDetails.getPassword() == null){
	
		// if(userDetails)
//
//		 pattern = Pattern.compile(EMAIL_PATTERN);
//		matcher = pattern.matcher(userDetails.getUserEmail().toString());
//		if(!matcher.matches()){
//			context.addMessage(null, new FacesMessage(""));
////			throw new ValidatorException(msg);
// 
//		}
		if (userDetails.getPassword().isEmpty()
				|| userDetails.getUserEmail().isEmpty()) {
			context.addMessage(null, new FacesMessage(
					"UserEmail/Password is empty"));
			flag = false;
//			System.out.println("not");
		}else if (userAccountDao.getCurrentUser(userDetails).isEmpty()) {
			context.addMessage(null, new FacesMessage("Login failed: Email address or Password is incorrect"));
			flag = false;
			// System.out.println("not");
		} else {
			userDetails = userAccountDao.getCurrentUser(userDetails).get(0);
			// userSession.setUser(userDetails);S
			// Cookie loginCookie = new Cookie("user",
			// userDetails.getFirstName());
			// loginCookie.setMaxAge(30*60);
			flag = true;
			System.out.println("yes");

		}
		return null;
	}

	/**
	 * Logs the current user out by invalidating the session.
	 * 
	 * @return &quot;logout&quot; which is used by the
	 *         {@literal faces-config.xml} to redirect back to the
	 *         {@literal home.xhtml} page.
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
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

	public Long getMemberAge() {
		Date currentDate = new Date();
		long diff = currentDate.getTime() - userDetails.getDate().getTime();

		System.out.println("Hours difference: " + (diff / (1000 * 60 * 60))
				+ " hours");
		return (diff / (1000 * 60 * 60 * 24));
	}

	public void setMemberAge(Long memberAge) {
		this.memberAge = memberAge;
	}

}
