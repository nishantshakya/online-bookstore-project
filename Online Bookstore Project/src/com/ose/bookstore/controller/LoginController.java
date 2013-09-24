/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginController() {

	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(this.username, this.password);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Login failed."));
			return "error";
		}
		return "admin/index";
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
	}
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
