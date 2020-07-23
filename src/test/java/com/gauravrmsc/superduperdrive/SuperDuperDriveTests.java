package com.gauravrmsc.superduperdrive;

import com.gauravrmsc.superduperdrive.pages.LoginPage;
import com.gauravrmsc.superduperdrive.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
    SuperDuperDrive.class})
class SuperDuperDriveTests {

  private static final String FIRST_NAME = "Gaurav";
  private static final String LAST_NAME = "Kumar";
  private static final String USERNAME = "Gaurav";
  private static final String PASSWORD = "007";
  private static final String LOGIN_URL = "/login";
  private static final String HOME_URL = "/home";
  private static final String SIGN_UP_URL = "/signup";
  private static final String HOME_TITLE = "Home";
  private static final String LOGIN_TITLE = "Login";
  private static final String SIGNUP_TITlE = "Sign Up";
  private static final String RESULT_TITLE = "Result";
  private static final String GOOGLE_URL = "www.google.com";
  public static final String FACEBOOK_URL = "ww.facebook.com";
  public static final String NOTE_TITLE = "Hello World";
  public static final String NOTE_DESC = "World Is Beautiful";
  public static final String UPDATED_TITLE = "Beautiful World";
  @LocalServerPort
  public int port;
  public String BASE_URL;
  private static WebDriver driver;
  private LoginPage loginPage;
  private SignupPage signupPage;

  @BeforeAll
  static void setup() {
    WebDriverManager.chromedriver().setup();


  }

  @BeforeEach
  public void reset() {
    driver = new ChromeDriver();
    BASE_URL = "http://localhost:" + port;
    loginPage = new LoginPage(driver);
    signupPage = new SignupPage(driver);
  }

  @AfterEach
  public void cleanUp() {
    driver.quit();
  }

  @Test
  public void loginTest() throws Exception {

    //Trying to navigate to Home Page without login
    driver.get(BASE_URL + HOME_URL);
    assertEquals(LOGIN_TITLE, driver.getTitle());
    Thread.sleep(100);

    //Trying to login as a nonRegistered User
    driver.get(BASE_URL + LOGIN_URL);
    loginPage.login(USERNAME, PASSWORD);
    assertEquals(LOGIN_TITLE, driver.getTitle());
    Thread.sleep(100);

    //User Signs Up
    driver.get(BASE_URL + SIGN_UP_URL);
    signupPage.signUp(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);
    Thread.sleep(1000);

    WebElement loginPageLink = driver.findElement(By.name("loginPageLink"));
    loginPageLink.click();
    loginPage.login(USERNAME, PASSWORD);
    assertEquals(HOME_TITLE, driver.getTitle());
    Thread.sleep(1000);
  }

  @Test
  public void notesFlowTest() throws InterruptedException {
    driver.get(BASE_URL + SIGN_UP_URL);
    signupPage.signUp(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);
    driver.get(BASE_URL + LOGIN_URL);
    loginPage.login(USERNAME, PASSWORD);
    WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
    WebElement showNoteModel = driver.findElement(By.id("show-note-modal-button"));
    WebElement noteTitle = driver.findElement(By.id("note-title"));
    WebElement noteDescription = driver.findElement(By.id("note-description"));
    WebElement saveNote = driver.findElement(By.id("save-note"));

    notesTab.click();
    Thread.sleep(1000);
    showNoteModel.click();
    Thread.sleep(1000);
    noteTitle.sendKeys(NOTE_TITLE);
    Thread.sleep(1000);
    noteDescription.sendKeys(NOTE_DESC);
    Thread.sleep(1000);
    saveNote.click();
    Thread.sleep(1000);

    WebElement homeReturn = driver.findElement(By.className("goToHomeLink"));

    homeReturn.click();
    Thread.sleep(1000);

    notesTab = driver.findElement(By.id("nav-notes-tab"));
    notesTab.click();
    Thread.sleep(1000);

    WebElement savedNote = driver.findElement(By.cssSelector("td.note-title-row"));
    WebElement editNote = driver.findElement(By.cssSelector("button.edit-note"));
    noteTitle = driver.findElement(By.id("note-title"));
    saveNote = driver.findElement(By.id("save-note"));
    Assertions.assertEquals(NOTE_TITLE, savedNote.getText());
    Thread.sleep(1000);
    editNote.click();
    Thread.sleep(1000);
    noteTitle.clear();
    Thread.sleep(1000);
    noteTitle.sendKeys(UPDATED_TITLE);
    saveNote.click();
    Thread.sleep(1000);


    homeReturn = driver.findElement(By.className("goToHomeLink"));
    homeReturn.click();
    Thread.sleep(1000);

    notesTab = driver.findElement(By.id("nav-notes-tab"));
    notesTab.click();
    Thread.sleep(1000);

    savedNote = driver.findElement(By.cssSelector("td.note-title-row"));
    WebElement deleteNote = driver.findElement(By.cssSelector("input.delete-note"));
    Assertions.assertEquals(UPDATED_TITLE, savedNote.getText());


    deleteNote.click();
    Thread.sleep(1000);

    homeReturn = driver.findElement(By.className("goToHomeLink"));
    homeReturn.click();
    Thread.sleep(1000);

    notesTab = driver.findElement(By.id("nav-notes-tab"));
    notesTab.click();
    Thread.sleep(1000);

    boolean ifNotePresent = driver.findElements(By.cssSelector("td.note-title-row")).isEmpty();
    Assertions.assertTrue(ifNotePresent);
  }

  @Test
  public void credentialFlowTest() throws InterruptedException {
    driver.get(BASE_URL + SIGN_UP_URL);
    signupPage.signUp(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);
    driver.get(BASE_URL + LOGIN_URL);
    loginPage.login(USERNAME, PASSWORD);

    WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
    WebElement showCredentialsModel = driver.findElement(By.id("show-credential-modal"));
    WebElement credentialUrl = driver.findElement(By.id("credential-url"));
    WebElement credentialUsername = driver.findElement(By.id("credential-username"));
    WebElement credentialPassword = driver.findElement(By.id("credential-password"));
    WebElement credentialSubmit = driver.findElement(By.id("credentialSubmit"));


    credentialsTab.click();
    Thread.sleep(1000);
    showCredentialsModel.click();
    Thread.sleep(1000);

    credentialUrl.sendKeys(GOOGLE_URL);
    Thread.sleep(1000);
    credentialUsername.sendKeys(USERNAME);
    Thread.sleep(1000);
    credentialPassword.sendKeys(PASSWORD);
    credentialSubmit.submit();
    Thread.sleep(1000);

    WebElement homeReturn = driver.findElement(By.className("goToHomeLink"));
    homeReturn.click();
    Thread.sleep(1000);
    credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
    Thread.sleep(1000);
    credentialsTab.click();

    Thread.sleep(1000);
    List<WebElement> editCredentialsButton =
        driver.findElements(By.cssSelector("button.edit-credential"));
    Thread.sleep(1000);
    List<WebElement> savedCredentialURL =
        driver.findElements(By.cssSelector("td.saved-credential-url"));
    Thread.sleep(1000);
    List<WebElement> savedCredentialUserName =
        driver.findElements(By.cssSelector("td.saved-credential-username"));
    Thread.sleep(1000);
    List<WebElement> savedCredentialPassword =
        driver.findElements(By.cssSelector("td.saved-credential-password"));
    Thread.sleep(1000);


    Thread.sleep(1000);
    Assertions.assertEquals(GOOGLE_URL, savedCredentialURL.get(0).getText());
    Assertions.assertEquals(USERNAME, savedCredentialUserName.get(0).getText());
    Assertions.assertEquals(PASSWORD, savedCredentialPassword.get(0).getText());
    Thread.sleep(1000);


    editCredentialsButton.get(0).click();
    Thread.sleep(1000);
    credentialUrl = driver.findElement(By.id("credential-url"));

    Thread.sleep(1000);
    credentialUrl.clear();
    Thread.sleep(1000);
    credentialUrl.sendKeys(FACEBOOK_URL);
    Thread.sleep(1000);
    credentialSubmit = driver.findElement(By.id("credentialSubmit"));
    Thread.sleep(1000);
    credentialSubmit.submit();
    Thread.sleep(1000);


    homeReturn = driver.findElement(By.className("goToHomeLink"));
    homeReturn.click();
    Thread.sleep(1000);

    credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
    credentialsTab.click();
    Thread.sleep(1000);
    savedCredentialURL = driver.findElements(By.cssSelector("td.saved-credential-url"));
    Thread.sleep(1000);

    Assertions.assertEquals(FACEBOOK_URL, savedCredentialURL.get(0).getText());

    List<WebElement> deleteCredentialButton =
        driver.findElements(By.cssSelector("input.delete-credential"));
    Thread.sleep(1000);
    deleteCredentialButton.get(0).click();
    Thread.sleep(1000);

    homeReturn = driver.findElement(By.className("goToHomeLink"));
    homeReturn.click();
    Thread.sleep(1000);

    credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
    credentialsTab.click();
    Thread.sleep(1000);

    boolean ifCredentialPresent =
        driver.findElements(By.cssSelector("td.saved-credential-url")).isEmpty();
    Assertions.assertTrue(ifCredentialPresent);
  }


}



