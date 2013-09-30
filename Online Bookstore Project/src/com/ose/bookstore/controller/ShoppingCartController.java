/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.ose.bookstore.dto.Cart;
import com.ose.bookstore.model.ejb.BookListDAO;
import com.ose.bookstore.model.ejb.ShoppingCartDAO;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;
import com.ose.bookstore.model.entity.UserDetails;
//import java.text.NumberFormat;

/**
 * Deals with all the page links dispatches present in orderBooks page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ShoppingCartDAO shoppingCartDao;

	@EJB
	BookListDAO bookListDao;

	@Inject
	ShoppingCart shoppingCart;
	
	@Inject 
	UserDetails currentUser;

	@NotNull(message = "Select the shipping type")
	String shippingType;// Selected shipping type(Standard or Express); atleast
						// one must be selected

	Cart scart;// Optimised & joint fields required for displaying in the
				// shopping cart page

	double tp;// Total price of the cart without shipping costs and taxes

	int bookQuantity;

	List<Cart> cartList;

	private double totalIncTax;

	private double totalWithShipping;

	/**
	 * The list containing the cart fields
	 * 
	 * @return cartList The shopping cart list
	 */
	public List<Cart> cartList() {
		System.out.println(currentUser.getFirstName());
		List<ShoppingCart> cart = shoppingCartDao.getCart(0);
		Books book = new Books();
		List<Cart> cartList = new ArrayList<Cart>();
		tp = 0.0;
		bookQuantity = 0;
		for (int i = 0; i < cart.size(); i++) {
			book = bookListDao.getBook(cart.get(i).getBookId());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
					.get(i).getBookQuantity());
			Cart cart1 = new Cart(
					book.getBookId(),
					book.getAuthor(),
					book.getTitle(),
					book.getEdition(),
					book.getPrice(),
					book.getDiscount(),
					cart.get(i).getBookQuantity(),
					((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
							.get(i).getBookQuantity()), book.getCoverPage(),cart.get(i).getScId());
			cartList.add(cart1);
			bookQuantity += cart.get(i).getBookQuantity();
		}
		return cartList;
	}

	public void delete(Cart cart) {
		ShoppingCart sc = new ShoppingCart();
		sc.setBookId(cart.getBookId());
		sc.setBookQuantity(cart.getQuantity());
		sc.setUserId(0);
		sc.setScId(cart.getCartId());
		shoppingCartDao.deleteEntry(sc);
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<Cart> getCartList() {
		return cartList();
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList();
	}

	public double getTp() {
		List<ShoppingCart> cart = shoppingCartDao.getCart(0);
		Books book = new Books();
		tp = 0.0;
		for (int i = 0; i < cart.size(); i++) {
			book = bookListDao.getBook(cart.get(i).getBookId());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
					.get(i).getBookQuantity());
		}
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
		System.out.println(shippingType);
	}

	/**
	 * Add the shipping costs and Taxes
	 * 
	 * @return the total price costs in string format
	 */
	public double getTotalIncTax() {

		totalIncTax = tp + tp * .035;
		return totalIncTax;

	}

	public void setTotalIncTax(double totalIncTax) {

		this.totalIncTax = totalIncTax;
		System.out.println(totalIncTax);
	}

	public double getTotalWithShipping() {
		totalWithShipping = tp + tp * .035;
		if (shippingType == null){
			shippingType = "Standard";
		}
		System.out.println("Excluding shipping: " + totalWithShipping);
		if (bookQuantity <= 3) {
			totalWithShipping = totalWithShipping + 7.5; //If less than 3 books, standard shipping
			System.out.println("Including Shipping: " + totalWithShipping);
			System.out.println(shippingType);
			
			if (shippingType.equals("Express")) {
				totalWithShipping = totalWithShipping + totalWithShipping * 0.3;//Express shipping, costs 30% more than standard shipping
			}
		}
		else {
			totalWithShipping = totalWithShipping + 9.5; //if 4 or more books, standard shipping
			System.out.println("Including Shipping: " + totalWithShipping);
			if (shippingType.equals("Express")) {
				totalWithShipping = totalWithShipping + totalWithShipping * 0.3;
				System.out.println("Excluding Tax: + shipping cost "
						+ totalWithShipping);
			}
		}
		System.out.println(totalWithShipping);
		System.out.println(shippingType);
		return totalWithShipping;
		
	}

	public void setTotalWithShipping(double totalWithShipping) {
		this.totalWithShipping = totalWithShipping;
		System.out.println(totalWithShipping);
	}

	public UserDetails getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDetails currentUser) {
		this.currentUser = currentUser;
	}

}
