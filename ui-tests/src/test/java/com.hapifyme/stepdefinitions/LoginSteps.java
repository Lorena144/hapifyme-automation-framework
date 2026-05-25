package com.hapifyme.stepdefinitions;

import com.hapifyme.context.TestContext;
import com.hapifyme.pages.LoginPage;
import com.hapifyme.services.AuthApiService;
import com.hapifyme.services.RegisterApiService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    private final static Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        loginPage.openLoginPage();
    }

    @When("user enters email {string}")
    public void userEntersEmail(String email) {
        loginPage.setEmail(email);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.setPassword(password);
    }

    @When("user logs in with valid credentials")
    public void userFillsInCredentials() throws IOException {

        FileInputStream file =
                new FileInputStream("src/main/resources/config/qa.properties");

        Properties properties =  new Properties();
        properties.load(file);

        loginPage.fillInCredentials(
                properties.getProperty("test.email"),
                properties.getProperty("test.password")
        );
    }

    @And("user clicks the login button")
    public void userClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user should be redirected to home page")
    public void userRedirectedToHomepage() {
        loginPage.verifyUserIsRedirectedToHomePage();
    }

    @Then("error message should be displayed {string}")
    public void errorMessageDisplayed(String errorMessage) {
        loginPage.verifyErrorMessageIsDisplayed(errorMessage);

    }

    @Given("user account is created via API")
    public void userAccountIsCreatedViaApi() {

        RegisterApiService userApiService = new RegisterApiService();
        userApiService.registerUser();

    }

    @When("user logs in with generated credentials")
    public void userLogsInWithGeneratedCredentials() {

        loginPage.openLoginPage();

        loginPage.fillInCredentials(
                TestContext.email,
                TestContext.password
        );

        loginPage.clickLoginButton();

        AuthApiService authApiService = new AuthApiService();

        authApiService.loginUser(TestContext.username, TestContext.password);
        logger.info("Logging into UI with API-generated credentials...");
    }


}


