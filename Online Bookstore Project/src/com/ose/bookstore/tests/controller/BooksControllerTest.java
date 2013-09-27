package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;


import com.ose.bookstore.controller.BooksController;
import com.ose.bookstore.model.ejb.BookListDao;
import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.Ratings;
import com.ose.bookstore.model.entity.ShoppingCart;

@RunWith(Arquillian.class)
public class BooksControllerTest {

	@Inject
	Books currentBook;
	@Inject
	ShoppingCart shoppingCart ;
	@Inject
	ShoppingCartDao shoppingCartDao;
	
	@Deployment
	public static JavaArchive createArchiveAndDeploy()
	{
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(BookListDao.class,Books.class,ShoppingCart.class,ShoppingCartDao.class,BooksController.class,Ratings.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}
	
	@Test
	public void testAddToShoppingCart() {
		Books mockedbooks = mock(Books.class);
		//Books book = new Books();
		//BooksController mockedbookscontroller = mock(BooksController.class);
		BooksController mockedbookscontroller = new BooksController();
		assertNotNull("book null",mockedbookscontroller);
		String test = mockedbookscontroller.addToShoppingCart();
		//Assert.assertNull("test is null",test);
		//verify(mockedbookscontroller).addToShoppingCart();
		Assert.assertEquals("shoppingCart",test);
		
	}

}
