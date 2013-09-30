package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ose.bookstore.controller.ShoppingCartController;
import com.ose.bookstore.helper.Cart;
import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;
import com.thoughtworks.selenium.DefaultSelenium;

@RunWith(Arquillian.class)
public class ShoppingCartControllerTest {
	
	ShoppingCartController shoppingCartController = new ShoppingCartController();
	List<Cart> cartlist = new ArrayList<Cart>();
	
	private static final String WEBAPP_SRC = "src/main/webapp/";
	
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "shoppingcart.war")
        		.addClasses(ShoppingCart.class,ShoppingCartController.class,ShoppingCartDao.class,Books.class,Cart.class)
        		.addAsWebResource(new File(WEBAPP_SRC,"bookProfile.xhtml"))
        		.addAsWebResource(new File(WEBAPP_SRC,"browseBooks.xhtml"))
        		.addAsWebResource(new File(WEBAPP_SRC,"shoppingCart.xhtml"))
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        		.addAsWebInfResource(
                new StringAsset("<faces-config version=\"2.0\"/>"),
                "faces-config.xml");
	}
	
	@Drone
    //DefaultSelenium browser;
	WebDriver browser = new FirefoxDriver();
	
	@ArquillianResource
    URL deploymentUrl ;

	
	@Test
	@InSequence(1)
	public void testCartList() {
		//ShoppingCartController mockshoppingcartcontroller = mock(ShoppingCartController.class);
		//when(mockshoppingcartcontroller.cartList()).thenReturn(List<Cart>);
		browser.get(deploymentUrl + "loginpage.xhtml");
		browser.findElement(By.xpath("//table[@id='j_idt46:grid:1:j_idt58']/tbody/tr/td/a/img")).click();
		browser.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    browser.findElement(By.xpath("//div[@id='dropDownForm:quantityValue_panel']/div/ul/li[2]")).click();
	    browser.findElement(By.id("dropDownForm:addToCartButton")).click();
	    
	    
	    cartlist = shoppingCartController.cartList();
	    
	    assertEquals("1",Integer.toString(cartlist.size()));
	    assertEquals("2",Integer.toString(cartlist.get(0).getBookId()));
	    assertEquals("2",Integer.toString(cartlist.get(0).getQuantity()));
	    
		
	}

	@Test
	@InSequence(2)
	public void testDelete() {
		shoppingCartController.delete(cartlist.get(0));
		assertTrue(cartlist.isEmpty());
	
	}

}
