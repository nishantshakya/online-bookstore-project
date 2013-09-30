package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ose.bookstore.controller.ShoppingCartController;
import com.ose.bookstore.controller.UserAccountController;
import com.ose.bookstore.helper.Cart;
import com.ose.bookstore.model.ejb.ShoppingCartDao;
import com.ose.bookstore.model.ejb.UserAccountDao;
import com.ose.bookstore.model.entity.Books;
import com.ose.bookstore.model.entity.ShoppingCart;
import com.ose.bookstore.model.entity.UserDetails;

@RunWith(Arquillian.class)
public class UserAccountControllerTest {
	
	UserAccountController userAccountController = new UserAccountController();
	UserDetails userdetails = new UserDetails();
	
	private static final String WEBAPP_SRC = "src/main/webapp/";
	
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "shoppingcart.war")
        		.addClasses(UserDetails.class,UserAccountController.class,UserAccountDao.class)
        		.addAsWebResource(new File(WEBAPP_SRC,"editDetails.xhtml"))
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        		.addAsWebInfResource(
                new StringAsset("<faces-config version=\"2.0\"/>"),
                "faces-config.xml");
	}

	@Test
	public void testEditDetails() {
		
		userdetails.setBuildingNo("1");userdetails.setCity("kathmandu");userdetails.setCountry("nepal");userdetails.setDate("27/11/13");userdetails.setFirstName("yubi");userdetails.setLastName("regmi");userdetails.setLoginId(1);
		userdetails.setMiddleName("regmi");userdetails.setPhone("11111");userdetails.setState("bagmati");userdetails.setStreetName("aaaaa");userdetails.setStreetNo("1");userdetails.setUserId(1);userdetails.setZip("1");
		
		userAccountController.editDetails();
		
	}

	@Test
	public void testShowDetails() {
		
	}

}
