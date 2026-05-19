package com.hapifyme.stepdefinitions;

import com.hapifyme.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

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

}


