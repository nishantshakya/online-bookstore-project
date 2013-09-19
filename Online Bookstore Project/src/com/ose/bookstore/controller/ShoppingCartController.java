/**
 * 
 */
package com.ose.bookstore.controller;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.ose.bookstore.helper.Cart;
import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * Deals with all the page links dispatches present in orderBooks page
 * 
 * @author OSE Nepal
 * @version 1.0 18 Sept 2013
 */
@Named
@RequestScoped
public class ShoppingCartController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ShoppingCartDao shoppingCartDao;

	@EJB
	BookListDao bookListDao;

	@Inject
	ShoppingCart shoppingCart;

	@NotNull(message = "Select the shipping type")
	int shippingType;//Selected shipping type(Standard or Express); atleast one must be selected

	Cart scart;//Optimised & joint fields required for displaying in the shopping cart page
	
	double tp;//Total price of the cart without shipping costs and taxes
	
	List<Cart> cartList;
	
	private double totalIncTax;
	
	/**The list containing the cart fields
	 * @return cartList The shopping cart list
	 */
	public List<Cart> cartList() {
		List<ShoppingCart> cart = shoppingCartDao.getCart(0);
		Books book = new Books();
		List<Cart> cartList = new ArrayList<Cart>();
		tp = 0.0;
		for (int i = 0; i < cart.size(); i++) {
			book = bookListDao.getBook(cart.get(i).getBookId());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
					.get(i).getBookQuantity());
			Cart cart1 = new Cart(book.getBookId(),book.getAuthor(),book.getTitle(),book.getEdition(),book.getPrice(),					book.getDiscount(),
					cart.get(i).getBookQuantity(),((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart.get(i).getBookQuantity()),book.getCoverPage());
			cartList.add(cart1);
		}
		System.out.println("Cart List: " + cartList);
		return cartList;
	}

	/**
	 * Converts the price to currency Format
	 * 
	 * @param price in double format
	 * @return currency in currency format
	 */
	public String convertToCurrency(double price) {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		System.out.println(currencyFormatter.format(price));
		return currencyFormatter.format(price);
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

	public String getTp() {
		List<ShoppingCart> cart = shoppingCartDao.getCart(0);
		Books book = new Books();
		tp = 0.0;
		for (int i = 0; i < cart.size(); i++) {
			book = bookListDao.getBook(cart.get(i).getBookId());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
					.get(i).getBookQuantity());
		}
		return convertToCurrency(tp);
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	public int getShippingType() {
		return shippingType;
	}

	public void setShippingType(int shippingType) {
		this.shippingType = shippingType;
		System.out.println(shippingType);
	}


	/**Add the shipping costs and Taxes
	 * @return the total price costs in string format
	 */
	public String getTotalIncTax() {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double totalExTax = tp + shippingType;
		System.out.println(totalExTax);
		totalIncTax = totalExTax + totalExTax * .15;
		System.out.println(currencyFormatter.format(totalIncTax));
		return convertToCurrency(totalIncTax);

	}

	public void setTotalIncTax(double totalIncTax) {

		this.totalIncTax = totalIncTax;
		System.out.println(totalIncTax);
	}

	
}
