package com.ose.bookstore.tests.frontend;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HomeTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHome() throws Exception {
    driver.get(baseUrl + "/Online_Bookstore_Project/faces/webpages/home.xhtml");
    driver.findElement(By.linkText("Book Store")).click();
    driver.findElement(By.linkText("View details of HTML5 & CSS3 Visual Quick Start Guide")).click();
    driver.findElement(By.cssSelector("span.ui-menuitem-text")).click();
    driver.findElement(By.id("j_idt55:search")).clear();
    driver.findElement(By.id("j_idt55:search")).sendKeys("html and cssssf");
    driver.findElement(By.cssSelector("span.ui-menuitem-text")).click();
    driver.findElement(By.linkText("user")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:lastNameInplace_display")).click();
    driver.findElement(By.id("j_idt53:j_idt67")).clear();
    driver.findElement(By.id("j_idt53:j_idt67")).sendKeys("asdasdas");
    driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
    driver.findElement(By.xpath("//div[@id='j_idt53:j_idt69_panel']/div/ul/li[13]")).click();
    driver.findElement(By.id("j_idt53:j_idt125")).click();
    driver.findElement(By.id("j_idt38:j_idt40")).click();
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
