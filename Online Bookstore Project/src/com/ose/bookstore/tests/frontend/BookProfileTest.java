package com.ose.bookstore.tests.frontend;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test class that performs front end testing on bookprofile page. 
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
public class BookProfileTest {
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
  public void testBookProfile() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("//table[@id='j_idt46:grid:1:j_idt58']/tbody/tr/td/a/img")).click();
    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
    driver.findElement(By.xpath("//div[@id='dropDownForm:quantityValue_panel']/div/ul/li[2]")).click();
    driver.findElement(By.id("dropDownForm:addToCartButton")).click();
    try {
      assertEquals("Shopping Cart", driver.getTitle());
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
