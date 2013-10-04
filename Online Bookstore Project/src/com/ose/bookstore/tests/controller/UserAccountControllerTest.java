package com.ose.bookstore.tests.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import javax.ejb.EJB;
import javax.inject.Inject;

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

import com.ose.bookstore.controller.HeaderController;
import com.ose.bookstore.controller.HomeController;
import com.ose.bookstore.controller.LoginController;
import com.ose.bookstore.controller.UserAccountController;
import com.ose.bookstore.model.ejb.CountryListDAO;

import com.ose.bookstore.model.ejb.UserAccountDAO;
import com.ose.bookstore.model.entity.CountryList;
import com.ose.bookstore.model.entity.Login;
import com.ose.bookstore.model.entity.UserDetails;
import static org.mockito.Mockito.*;

/**
 * Test method that performs integration testing on user registration and edit details
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@RunWith(Arquillian.class)
public class UserAccountControllerTest {
	
	@Inject
	UserAccountController userAccountController;
	
	@Inject
	UserDetails userdetails;
	
	@EJB
	UserAccountDAO userAccountDAO;
	
	private static final String WEBAPP_SRC = "webpages/";
	
	/**
	 * Method that creates archive for micro deployment
	 * @return ShrinkWrap archive
	 */
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "shoppingcart.war")
        		.addClasses(UserDetails.class,Login.class,CountryList.class,HeaderController.class,HomeController.class,LoginController.class,UserAccountController.class,CountryListDAO.class,UserAccountDAO.class)
        		.addAsWebResource(new File(WEBAPP_SRC,"editDetails.xhtml"))
        		.addAsWebResource(new File(WEBAPP_SRC,"home.xhtml"))
        		.addAsWebResource(new File(WEBAPP_SRC,"userRegistration.xhtml"))
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        		.addAsManifestResource("META-INF/persistence.xml")
        		.addAsWebInfResource(
                new StringAsset("<faces-config version=\"2.0\"/>"),
                "faces-config.xml");
	}
	
	@Drone
    //DefaultSelenium browser;
	WebDriver browser = new FirefoxDriver();
	
	@ArquillianResource
    URL deploymentUrl ;

	/**
	 * Test method that executes first and tests method editDetails() 
	 */
	@Test
	@InSequence(1)
	public void testUsingMocks()
	{
		UserAccountController mockeduseraccountcontroller = mock(UserAccountController.class);
		mockeduseraccountcontroller.editDetails();
		verify(mockeduseraccountcontroller).editDetails();
	}
	
	/**
	 * Test method that executes second and performs integration test on user registration
	 */
	@Test
	@InSequence(2)
	public void testRegisterUser() {
		
		int totalAccounts = userAccountDAO.getAccountList().size();

		browser.get(deploymentUrl + "home.xhtml");
		browser.findElement(By.id("signUpId:j_idt41")).clear();
	    browser.findElement(By.id("signUpId:j_idt41")).sendKeys("Yubi");
	    browser.findElement(By.id("signUpId:j_idt43")).clear();
	    browser.findElement(By.id("signUpId:j_idt43")).sendKeys("Regmi");
	    browser.findElement(By.id("signUpId:email1")).clear();
	    browser.findElement(By.id("signUpId:email1")).sendKeys("ube@gmail.com");
	    browser.findElement(By.id("signUpId:password1")).clear();
	    browser.findElement(By.id("signUpId:password1")).sendKeys("ube");
	    browser.findElement(By.id("signUpId:password2")).clear();
	    browser.findElement(By.id("signUpId:password2")).sendKeys("ube");
	    browser.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
	    browser.findElement(By.xpath("//div[@id='signUpId:j_idt54_panel']/div/ul/li[143]")).click();
	    browser.findElement(By.id("signUpId:j_idt63")).clear();
	    browser.findElement(By.id("signUpId:j_idt63")).sendKeys("bagmati");
	    browser.findElement(By.id("signUpId:j_idt67")).clear();
	    browser.findElement(By.id("signUpId:j_idt67")).sendKeys("kathmandu");
	    browser.findElement(By.id("signUpId:j_idt71")).clear();
	    browser.findElement(By.id("signUpId:j_idt71")).sendKeys("koteshwor");
	    browser.findElement(By.id("signUpId:j_idt83")).clear();
	    browser.findElement(By.id("signUpId:j_idt83")).sendKeys("1");
	    browser.findElement(By.name("signUpId:j_idt87")).click();
	    
	    assertEquals("Home", browser.getTitle());
		assertEquals(1,userAccountDAO.getAccountList().size()-totalAccounts);
	}
	
	
	/**
	 * Test method that executes third and performs integration testing on edit details
	 */
	@Test
	@InSequence(3)
	public void testEditDetails() {
		
		
		browser.get(deploymentUrl + "home.xhtml");
		browser.findElement(By.id("form1:email")).clear();
		browser.findElement(By.id("form1:email")).sendKeys("ube@gmail.com");
		browser.findElement(By.id("form1:password")).clear();
	    browser.findElement(By.id("form1:password")).sendKeys("ube");
	    browser.findElement(By.id("form1:j_idt21")).click();
	    assertTrue(browser.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome Yubi[\\s\\S]*$"));
	    browser.findElement(By.linkText("Welcome Yubi")).click();
	    browser.findElement(By.id("j_idt55:j_idt66")).clear();
	    browser.findElement(By.id("j_idt55:j_idt66")).sendKeys("Nishant");
	    browser.findElement(By.id("j_idt55:j_idt105")).click();
	    assertEquals("Nishant",userAccountController.getUserDetails().getFirstName());
		
	}

	

}
