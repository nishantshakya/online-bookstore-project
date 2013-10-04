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
	
	private int activeIndex; /*navigation bar index*/
	
	public HeaderController() {
	}
	
	public String navigatePage(int activeIndex)
	{
		if(activeIndex == 0)
		{
			this.activeIndex = 0;
			return "/webpages/home?faces-redirect=true";
		}
		else if(activeIndex == 1)
		{
			this.activeIndex = 1;
			return "/webpages/browsebooks?faces-redirect=true";
		}
		else if (activeIndex == 2)
		{
			this.activeIndex = 2;
			return "/webpages/shoppingCart?faces-redirect=true";
		}
		else 
			return "/webpages/home?faces-redirect=true";
	}

	public String goToRegistration(){
		return "/webpages/userRegistration?faces-redirect=true";
	}

	//Getters and Setters
	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

}
