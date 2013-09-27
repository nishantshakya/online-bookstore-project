/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * @author nishant
 * 
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	// @Inject
	// Login login;

	@EJB
	UserAccountDao userAccountDao;
	
	@Inject
	UserDetails userDetails;
	
	private static final long serialVersionUID = 1L;

//	private String name;
//	private String password;
	private boolean flag = true;

	public String checkLogin(){
		UserDetails currentUser;
		System.out.println(userDetails.getUserEmail());
		currentUser = userAccountDao.getCurrentUser(userDetails);
		System.out.println(currentUser.getUserEmail());
		if (currentUser.equals(null)){
			flag = true;
		}else{
			flag = false;
		}
		return null;
	}
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
////		flag = false;
////		System.out.println(name);
////		System.out.println(flag);
//	}
	

	// public String checkLogin(){
	// System.out.println("sfdsaD");
	// flag = false;
	// return "home";
	// // System.out.println(flag);
	// // return flag;
	// }

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "home?faces-redirect=true";
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

//	public boolean isFlag() {
//		return flag;
//	}
//
//	public void setFlag(boolean flag) {
//		this.flag = true;
//	}
	//
	// public Login getLogin() {
	// return login;
	// }
	//
	// public void setLogin(Login login) {
	// this.login = login;
	// }

	/*
	 * private String username; private String password;
	 * 
	 * public String getUsername() { return this.username; }
	 * 
	 * public void setUserName(String username) { this.username = username; }
	 * 
	 * public String getPassword() { return this.password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public LoginController() {
	 * 
	 * }
	 * 
	 * public String login() { FacesContext context =
	 * FacesContext.getCurrentInstance(); HttpServletRequest request =
	 * (HttpServletRequest) context .getExternalContext().getRequest(); try {
	 * request.login(this.username, this.password); } catch (ServletException e)
	 * { context.addMessage(null, new FacesMessage("Login failed.")); return
	 * "error"; } return "admin/index"; }
	 * 
	 * public void logout() { FacesContext context =
	 * FacesContext.getCurrentInstance(); HttpServletRequest request =
	 * (HttpServletRequest) context .getExternalContext().getRequest(); try {
	 * request.logout(); } catch (ServletException e) { context.addMessage(null,
	 * new FacesMessage("Logout failed.")); } }
	 */
	/**
	 * Logs the current user out by invalidating the session.
	 * 
	 * @return &quot;logout&quot; which is used by the
	 *         {@literal faces-config.xml} to redirect back to the
	 *         {@literal index.xhtml} page.
	 */
	// public String logout() {
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// ExternalContext externalContext = facesContext.getExternalContext();
	// externalContext.invalidateSession();
	// return "logout";
	// }

}
