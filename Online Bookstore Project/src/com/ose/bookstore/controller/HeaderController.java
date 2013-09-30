/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Deals with the page links dispatches present in the navigation bar and header
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@SessionScoped
public class HeaderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderController() {
	}

	public String generateCart() {

		return "shoppingCart?faces-redirect=true";
	}

	public String goToHome() {
		return "home?faces-redirect=true";
	}

	public String goToBooks() {
		return "browsebooks?faces-redirect=true";
	}

	public String goToRegistration(){
		return "userRegistration?faces-redirect=true";
	}

}
