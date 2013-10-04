package com.ose.bookstore.tests.frontend;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test class that performs frontend test on browsing of books 
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
public class BrowseBooks {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:9090/Online_BookStore_Project/faces/webpages/browseBooks.xhtml";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBrowseBooks() throws Exception {
    //driver.get(baseUrl + "/Online_BookStore_Project/faces/webpages/browseBooks.xhtml");
	  driver.get(baseUrl);
	 // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Browse Books[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//table[@id='j_idt46:grid:1:j_idt58']/tbody/tr/td/a/img")).click();
    driver.findElement(By.cssSelector("#j_idt33 > span.ui-menuitem-text")).click();
    driver.findElement(By.xpath("//table[@id='j_idt46:grid:2:j_idt58']/tbody/tr/td/a/img")).click();
    driver.findElement(By.cssSelector("#j_idt33 > span.ui-menuitem-text")).click();
    driver.findElement(By.cssSelector("#j_idt38 > span.ui-menuitem-text")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*SHOPPING CART[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
