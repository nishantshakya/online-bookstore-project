package com.ose.bookstore.model.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import com.ose.bookstore.model.entity.ShoppingCart;

/**
 * Session Bean implementation class TempShoppingCart 
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@Stateful
@LocalBean
@SessionScoped
public class TempShoppingCartDAO {

    
    private List<ShoppingCart> tempCart;

    public TempShoppingCartDAO() {
    }

    /**Adds books to cart list
     * @param shoppingCart
     */
    public void add(ShoppingCart shoppingCart){
    	List<ShoppingCart> sCart = getCart();//sCart contains the List of all the entries in the shoppingCart table
    	
		//Checks the table for existing books in the table; bookQuantity to be updated accordingly
		for (int i = 0; i < sCart.size(); i++) {
			if (sCart.get(i).getBookId() == shoppingCart.getBookId()){
				shoppingCart.setBookQuantity(shoppingCart.getBookQuantity()+ sCart.get(i).getBookQuantity());
				tempCart.remove(i);
			}
		}
    	tempCart.add(shoppingCart);
    }
    
    public List<ShoppingCart> getCart(){
		return tempCart;
    }
    
    @PostConstruct
    public void init(){
    	tempCart = new ArrayList<>();
    }

	public List<ShoppingCart> getTempCart() {
		return tempCart;
	}

	public void setTempCart(List<ShoppingCart> tempCart) {
		this.tempCart = tempCart;
	}
}