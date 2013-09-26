
package com.ose.bookstore.tests;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    driver.get(baseUrl + "/Online_Bookstore_Project/");
    driver.findElement(By.id("j_idt45:j_idt48")).click();
    driver.findElement(By.id("j_idt59:search")).clear();
    driver.findElement(By.id("j_idt59:search")).sendKeys("sf");
    driver.findElement(By.cssSelector("span.ui-menuitem-text")).click();
    driver.findElement(By.linkText("View details of HTML5 & CSS3 Visual Quick Start Guide")).click();
    driver.findElement(By.xpath("//div[@id='j_idt84:j_idt86']/div[2]/a")).click();
    driver.findElement(By.id("j_idt87:j_idt92_label")).click();
    driver.findElement(By.name("j_idt87:j_idt95")).click();
    driver.findElement(By.id("j_idt84:checkOutId")).click();
    driver.findElement(By.id("j_idt47:j_idt70")).clear();
    driver.findElement(By.id("j_idt47:j_idt70")).sendKeys("Nishant");
    driver.findElement(By.xpath("//table[@id='j_idt47:shippingType']/tbody/tr/td/div/div[2]")).click();
    driver.findElement(By.id("j_idt47:shippingType:0")).click();
    driver.findElement(By.name("j_idt47:j_idt91")).click();
    driver.findElement(By.name("j_idt23:j_idt57")).click();
    driver.findElement(By.linkText("Click here to return to home page.")).click();
    driver.findElement(By.id("j_idt45:j_idt50")).click();
    driver.findElement(By.linkText("delete")).click();
    driver.findElement(By.id("j_idt84:checkOutId")).click();
    driver.findElement(By.id("j_idt47:j_idt82")).clear();
    driver.findElement(By.id("j_idt47:j_idt82")).sendKeys("Lalitpur");
    driver.findElement(By.name("j_idt47:j_idt91")).click();
    driver.findElement(By.xpath("//table[@id='j_idt47:shippingType']/tbody/tr/td/div/div[2]")).click();
    driver.findElement(By.id("j_idt47:shippingType:0")).click();
    driver.findElement(By.name("j_idt47:j_idt91")).click();
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
