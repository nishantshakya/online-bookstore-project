/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.dto.Cart;
import com.ose.bookstore.model.ejb.OrderDAO;
import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.OrderDetail;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * Deals with all the page links dispatches present in orderBooks page
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Named
@SessionScoped
public class OrderController implements Serializable {

	@EJB
	OrderDAO orderDao;
	
	@EJB
	UserAccountDAO userAccountDao;
	
	UserDetails currentUser;
	
	Cart currentCart;
	
	@Inject
	OrderDetail order;

	private static final long serialVersionUID = 1L;

	/**Sets the order object to store in the database later
	 * @param userDetails current user details from the <b><u>orderbooks</u></b> page
	 * @param shippingType current shipping type from the <b><u>orderbooks</u></b> page
	 * @param cartList current cart list of the user from the <b><u>orderbooks</u></b> page
	 * @return
	 */
	public String action(UserDetails userDetails, String shippingType,	List<Cart> cartList) {
		this.currentUser = userDetails;
		userAccountDao.editUser(userDetails);
		for (int i = 0; i < cartList.size(); i++) {
			order.setShippingId(i);
			Date d1 = new Date();
			order.setDate(d1);
			order.setBookId(cartList.get(i).getBookId());
			order.setUserId(userDetails.getUserId());
			order.setStatus(i);
			orderDao.create(order);
		}
		return "creditCard?faces-redirect=true";
	}

	//Getters and Setters
	public UserDetails getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDetails currentUser) {
		this.currentUser = currentUser;
	}


	public Cart getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(Cart currentCart) {
		this.currentCart = currentCart;
	}

	public OrderDetail getOrder() {
		return order;
	}

	public void setOrder(OrderDetail order) {
		this.order = order;
	}

}
