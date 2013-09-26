/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ose.bookstore.helper.Cart;
import com.ose.bookstore.model.ejb.OrderDao;
import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.OrderDetail;
import com.ose.bookstore.model.entity.ShippingType;
import com.ose.bookstore.model.entity.UserDetails;

/**
 * Deals with all the page links dispatches present in orderBooks page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class OrderController implements Serializable {

	/**
	 * 
	 */
	@EJB
	OrderDao orderDao;
	
	@EJB
	UserAccountDao userAccountDao;
	
	UserDetails currentUser;
	
	ShippingType currentShipping;
	
	Cart currentCart;
	
	@Inject
	OrderDetail order;

	private static final long serialVersionUID = 1L;

	/**Sets the order object to store in the database later
	 * @param userDetails current user details from the orderbooks page
	 * @param shippingType current shipping type from the orderbooks page
	 * @param cartList current cart list of the user from the orderbooks page
	 * @return
	 */
	public String action(UserDetails userDetails, String shippingType,	List<Cart> cartList) {
		this.currentUser = userDetails;
		userAccountDao.editUser(userDetails);
		for (int i = 0; i < cartList.size(); i++) {
			order.setShippingId(i);
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d1 = new Date();
//			String formattedDate = dateFormat.format(d1);
			order.setDate(d1);
			order.setBookId(cartList.get(i).getBookId());
			order.setUserId(userDetails.getUserId());
			order.setStatus(i);
			orderDao.create(order);

		}
		return "creditCard";
	}

	//Getters and Setters
	public UserDetails getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDetails currentUser) {
		this.currentUser = currentUser;
	}

	public ShippingType getCurrentShipping() {
		return currentShipping;
	}

	public void setCurrentShipping(ShippingType currentShipping) {
		this.currentShipping = currentShipping;
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
