package com.ose.bookstore.tests.frontend;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test class that performs frontend test on shopping cart
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
public class ShoppingCartTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:9090/Online_BookStore_Project/faces/webpages/shoppingCart.xhtml";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testShoppingCart() throws Exception {
    driver.get(baseUrl );
    driver.findElement(By.linkText("user")).click();
    driver.findElement(By.cssSelector("#j_idt36 > span.ui-menuitem-text")).click();
    driver.findElement(By.id("checkoutForm:checkOutId")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
}
