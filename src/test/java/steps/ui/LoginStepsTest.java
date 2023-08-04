package steps.ui;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.StudymateLoginPage;
import utilities.ApplicationFlow;
import utilities.Config;
import utilities.Driver;
import utilities.PasswordEncryptorDecryptor;

public class LoginStepsTest {

    WebDriver driver= Driver.getDriver();
    StudymateLoginPage loginPage= new StudymateLoginPage();
    final static Logger logger= Logger.getLogger(LoginStepsTest.class);


    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver.get(Config.getValue("studymateBaseURL"));

        logger.info("User"+Config.getValue("studyMateEmail")+" is on login page ");
        Assert.assertEquals("StudyMate", driver.getTitle());
    }


    @When("user enters email {string}")
    public void user_enters_email(String email) {
    loginPage.emailInput.sendKeys(email);
    logger.info("User entered email: " + Config.getValue("studyMateEmail"));
    }


    @When("user enters password {string}")
    public void user_enters_password(String password) {
    loginPage.passwordInput.sendKeys(password);
        logger.info("User entered pasword: " + Config.getValue("studyMateEmail"));

    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
    loginPage.loginBtn.click();
    logger.info("User clicked on login button");
    }

    @Then("verify that user must be logged in")
    public void verify_that_user_must_be_logged_in() {
        logger.info("Waiting for 3 seconds");
        ApplicationFlow.pause(2000);
    Assert.assertEquals(Config.getValue("studymateLandingPage"), driver.getCurrentUrl());
    logger.warn("This is my warn message");
    logger.error("Oops, error message");
    }

    @When("user enters correct email")
    public void user_enters_correct_email() {
    loginPage.emailInput.sendKeys(Config.getValue("studyMateEmail"));

    }
    @When("user enters correct password")
    public void user_enters_correct_password() {
    loginPage.passwordInput.sendKeys(PasswordEncryptorDecryptor.decryptPassword(Config.getValue("studyMatePassword")));
    }
}
