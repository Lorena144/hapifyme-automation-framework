package com.hapifyme.stepdefinitions;

import com.hapifyme.models.RegisterUser;
import com.hapifyme.pages.LoginPage;
import com.hapifyme.pages.RegisterPage;
import com.hapifyme.utils.DataGenerator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class RegisterSteps {
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();

    @Given("user is on register page")
    public void userAccessRegisterForm() {
        loginPage.openLoginPage();
        loginPage.navigateToRegisterPage();
    }

    @When("user fills register form")
    public void userFillsRegisterForm(DataTable table) {
        Map<String, String> data = table.asMap();

        RegisterUser user = new RegisterUser();

        // Generate unique email for each execution
        String uniqueEmail =
                DataGenerator.generateRandomEmail();

        user.setFirstName(data.get("firstName"));
        user.setLastName(data.get("lastName"));
        user.setEmail(uniqueEmail);
        user.setConfirmEmail(uniqueEmail);
        user.setPassword(data.get("password"));
        user.setConfirmPassword(data.get("confirmPassword"));

        registerPage.fillRegisterForm(user);

    }

    @And("user clicks the register button")
    public void userClickRegisterButton() {

        registerPage.clickRegisterButton();
    }

    @Then("the successful registration message should be displayed")
    public void verifySuccessfulRegistrationMessage() {
        registerPage.verifySuccessfulRegistrationMessage();
    }
}
