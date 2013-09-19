/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Deals with the page links dispatches present in the navigation bar and header
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class HeaderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderController() {
	}

	public String generateCart() {

		return "shoppingCart";
	}

	public String goToHome() {
		return "home";
	}

	public String goToBooks() {
		return "browsebooks";
	}

}
