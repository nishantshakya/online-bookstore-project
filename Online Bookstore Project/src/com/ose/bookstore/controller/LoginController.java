/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

//import com.ose.bookstore.dto.UserSession;
import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * @author nishant
 * 
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	// @Inject
	// Login login;

	@EJB
	UserAccountDAO userAccountDao;

	@Inject
	UserDetails userDetails;
	
//	 private UserSession userSession;
	 
	private static final long serialVersionUID = 1L;

	private boolean flag = false;

	public String checkLogin() {

		FacesContext context = FacesContext.getCurrentInstance();
		if (userAccountDao.getCurrentUser(userDetails).isEmpty()) {
			context.addMessage(null, new FacesMessage("Login failed."));
			flag = false;
		} else {
			userDetails = userAccountDao.getCurrentUser(userDetails).get(0);
//			userSession.setUser(userDetails);
//			Cookie loginCookie = new Cookie("user", userDetails.getFirstName());
//			loginCookie.setMaxAge(30*60);
			flag = true;
		}
		return null;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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
//		Cookie loginCookie = null;
//		loginCookie.setMaxAge(0);
		flag = false;
		return "home?faces-redirect=true";
	}

	public String signUp() {
		return "userRegistration";
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

//	   public boolean isLoggedIn() {
//	        return userDetails != null;
//	    }

}
