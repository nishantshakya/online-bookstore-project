package com.ose.bookstore.tests.frontend;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserProfileTest {
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
  public void testUserProfile() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.linkText("user")).click();
    driver.findElement(By.id("j_idt43:j_idt50")).clear();
    driver.findElement(By.id("j_idt43:j_idt50")).sendKeys("yubi");
    driver.findElement(By.id("j_idt43:j_idt52")).clear();
    driver.findElement(By.id("j_idt43:j_idt52")).sendKeys("regmi");
    driver.findElement(By.id("j_idt43:j_idt62")).clear();
    driver.findElement(By.id("j_idt43:j_idt62")).sendKeys("kathmandu");
    driver.findElement(By.id("j_idt43:j_idt66")).clear();
    driver.findElement(By.id("j_idt43:j_idt66")).sendKeys("11111");
    driver.findElement(By.id("j_idt43:j_idt70")).clear();
    driver.findElement(By.id("j_idt43:j_idt70")).sendKeys("22222");
    driver.findElement(By.id("j_idt43:j_idt71")).click();
    try {
      assertEquals("yubi", driver.findElement(By.id("j_idt43:j_idt50")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("regmi", driver.findElement(By.id("j_idt43:j_idt52")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("kathmandu", driver.findElement(By.id("j_idt43:j_idt62")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("11111", driver.findElement(By.id("j_idt43:j_idt66")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("22222", driver.findElement(By.id("j_idt43:j_idt70")).getAttribute("value"));
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
