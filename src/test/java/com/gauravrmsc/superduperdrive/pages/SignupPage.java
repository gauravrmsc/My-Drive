package com.gauravrmsc.superduperdrive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;


public class SignupPage {

  @FindBy(name = "firstName")
  private WebElement firstNameField;
  @FindBy(name = "lastName")
  private WebElement lastNameField;
  @FindBy(name = "username")
  private WebElement usernameField;
  @FindBy(name = "password")
  private WebElement passwordField;
  @FindBy(name = "signupButton")
  private WebElement signUpButton;

  public SignupPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void signUp(String firstName, String lastName, String username, String password) {
    firstNameField.sendKeys(firstName);
    lastNameField.sendKeys(lastName);
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signUpButton.submit();
  }
}
