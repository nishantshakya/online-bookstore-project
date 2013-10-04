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
import com.ose.bookstore.model.ejb.TempShoppingCartDAO;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;
import com.ose.bookstore.model.entity.UserDetails;



/**
 * Deals with all the page links dispatches present in shoppingCart page
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ShoppingCartDAO shoppingCartDao;

	@EJB
	BookListDAO bookListDao;

	@Inject
	TempShoppingCartDAO tempShoppingCartDao;

	@Inject
	UserAccountController userAccountController;

	@Inject
	ShoppingCart shoppingCart;

	@Inject
	UserDetails currentUser;

	@NotNull(message = "Select the shipping type")
	String shippingType; /*Standard or Express*/

	Cart scart;/*DTO for shopping cart*/

	double tp;/* Total price of the cart without shipping costs and taxes*/

	int bookQuantity;/*Total books in a cart*/

	List<Cart> cartList;/*Shopping cart list*/

	private double totalIncTax;/*Total cost including taxes*/

	private double totalWithShipping;/*Total cost inclusive of taxes and shipping costs*/

	private final double TAX = 0.035;
	
	private final int THRESHOLD_BOOK_QUANTITY = 3;
	
	private final double LOWCOST = 7.5;/*for less than and equal to 3 books*/
	
	private final double HIGHCOST = 9.5;/*for more than 3 books*/
	
	private final double EXTRAEXPRESSCOST = 0.3;/*Express shipping costs 30% more than shipping*/
	
	/**
	 * The list containing the cart fields
	 * 
	 * @return cartList The shopping cart list
	 */
	public List<Cart> cartList() {
		List<ShoppingCart> cart = null;
		
		if (userAccountController.isFlag()) {
			cart = shoppingCartDao.getCart(userAccountController.getUserDetails().getUserId());
		} else {
			cart = tempShoppingCartDao.getCart();
		}
		
		Books book = new Books();
		List<Cart> cartList = new ArrayList<Cart>();
		tp = 0.0;
		bookQuantity = 0;
		
		for (int i = 0; i < cart.size(); i++) { /*transfers shopping cart to DTO cart object*/
			book = bookListDao.getBook(cart.get(i).getBookId());
			System.out.println("Current book via boolist: " + book.getTitle());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart
					.get(i).getBookQuantity());
			Cart cart1 = new Cart(book.getBookId(),book.getAuthor(),book.getTitle(),book.getEdition(),book.getPrice(),
					book.getDiscount(),cart.get(i).getBookQuantity(),
					((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart.get(i).getBookQuantity()), 
					book.getCoverPage(),cart.get(i).getScId());
			cartList.add(cart1);
			bookQuantity += cart.get(i).getBookQuantity();
		}
		return cartList;
	}

	/**Deletes a cart entry from the shopping cart
	 * @param cart
	 */
	public void delete(Cart cart) {
		ShoppingCart sc = new ShoppingCart();
		sc.setBookId(cart.getBookId());
		sc.setBookQuantity(cart.getQuantity());
		sc.setUserId(userAccountController.getUserDetails().getUserId());
		sc.setScId(cart.getCartId());
		shoppingCartDao.deleteEntry(sc);
	}

	//Getters and Setters
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

	/**Total price of a cart
	 * @return total price with discount 
	 */
	public double getTp() {
		List<ShoppingCart> cart = shoppingCartDao.getCart(userAccountController
				.getUserDetails().getUserId());
		Books book = new Books();
		tp = 0.0;
		for (int i = 0; i < cart.size(); i++) {
			book = bookListDao.getBook(cart.get(i).getBookId());
			tp += ((book.getPrice() - (book.getPrice() * book.getDiscount())) * cart.get(i).getBookQuantity());
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
	}

	/**
	 * Add the Taxes to total cost
	 * 
	 * @return the total price cost in string format
	 */
	public double getTotalIncTax() {
		totalIncTax = tp + tp * TAX;
		return totalIncTax;
	}

	public void setTotalIncTax(double totalIncTax) {
		this.totalIncTax = totalIncTax;
	}

	/**Total price with shipping costs
	 * @return 
	 */
	public double getTotalWithShipping() {
		totalWithShipping = tp + tp * TAX;
		if (shippingType == null) {
			shippingType = "Standard";
		}
		
		if (bookQuantity <= THRESHOLD_BOOK_QUANTITY) {
			totalWithShipping = totalWithShipping + LOWCOST; 
			if (shippingType.equals("Express")) {
				totalWithShipping = totalWithShipping + totalWithShipping * EXTRAEXPRESSCOST;
			}
		} else {
			totalWithShipping = totalWithShipping + HIGHCOST; 
			if (shippingType.equals("Express")) {
				totalWithShipping = totalWithShipping + totalWithShipping * EXTRAEXPRESSCOST;
			}
		}
		
		return totalWithShipping;

	}

	public void setTotalWithShipping(double totalWithShipping) {
		this.totalWithShipping = totalWithShipping;
	}

	public UserDetails getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDetails currentUser) {
		this.currentUser = currentUser;
	}

}
