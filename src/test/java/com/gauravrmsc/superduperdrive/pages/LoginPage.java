package com.gauravrmsc.superduperdrive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

  @FindBy(name = "username")
  private WebElement userNameField;
  @FindBy(name = "password")
  private WebElement passwordField;
  @FindBy(tagName = "button")
  private WebElement loginButton;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void login(String username, String password) {
    userNameField.sendKeys(username);
    passwordField.sendKeys(password);
    loginButton.submit();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
