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

	private String hom1;
	
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

	public String getHom1() {
		return "faces/webpages/home.xhtml";
	}

	public void setHom1(String hom1) {
		this.hom1 = hom1;
	}

}
