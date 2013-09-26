package com.ose.bookstore.tests.frontend;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BookProfileTest {
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
  public void testBookProfile() throws Exception {
    driver.get(baseUrl + "/Online_Bookstore_Project/faces/webpages/bookProfile.xhtml?id=1");
    driver.findElement(By.xpath("//div[@id='j_idt78:j_idt80']/div[5]/a")).click();
    driver.findElement(By.name("j_idt81:j_idt89")).click();
    driver.findElement(By.xpath("//a[@id='j_idt39:j_idt42']/span")).click();
    driver.findElement(By.xpath("//table[@id='j_idt53:grid:1:j_idt64']/tbody/tr/td/a/img")).click();
    driver.findElement(By.xpath("//div[@id='j_idt78:j_idt80']/div[2]/a")).click();
    driver.findElement(By.id("j_idt81:j_idt86_label")).click();
    driver.findElement(By.xpath("//div[@id='j_idt81:j_idt86_panel']/div/ul/li[3]")).click();
    driver.findElement(By.name("j_idt81:j_idt89")).click();
    driver.findElement(By.xpath("//a[@id='j_idt39:j_idt42']/span")).click();
    driver.findElement(By.cssSelector("img")).click();
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
