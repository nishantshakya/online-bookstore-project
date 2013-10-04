package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.event.RateEvent;

import static org.mockito.Mockito.*;


import com.ose.bookstore.controller.BooksController;
import com.ose.bookstore.controller.UserAccountController;
import com.ose.bookstore.model.ejb.BookListDAO;
import com.ose.bookstore.model.ejb.ShoppingCartDAO;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.Ratings;
import com.ose.bookstore.model.entity.ShoppingCart;



/**
 *Test class that tests method of BookController 
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@RunWith(Arquillian.class)
public class BooksControllerTest {

	@Inject
	Books currentBook;
	@Inject
	ShoppingCart shoppingCart ;
	@Inject
	ShoppingCartDAO shoppingCartDao;
	
	
	
	/**
	 * Method that creates archive for micro deployment 
	 * @return ShrinkWrap archive
	 */
	@Deployment(testable=false)
	public static WebArchive createArchiveAndDeploy()
	{
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(BookListDAO.class,Books.class,ShoppingCart.class,ShoppingCartDAO.class,BooksController.class,Ratings.class,UserAccountController.class)
				.addAsManifestResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
		                new StringAsset("<faces-config version=\"2.0\"/>"),
		                "faces-config.xml");
	}
	
	/**
	 * Test method
	 */
	@Test
	public void testAddToShoppingCart() {
		Books mockedbooks = mock(Books.class);
		BooksController mockedbookscontroller = mock(BooksController.class);
		RateEvent mockedrateevent = mock(RateEvent.class);
		assertNotNull("book null",mockedbookscontroller);
		String test = mockedbookscontroller.addToShoppingCart();
		mockedbookscontroller.onrate(mockedrateevent);
		
		verify(mockedbookscontroller).addToShoppingCart();
		verify(mockedbookscontroller).onrate(mockedrateevent);
		
		
	}

}
