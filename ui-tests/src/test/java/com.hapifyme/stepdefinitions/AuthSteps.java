package com.hapifyme.stepdefinitions;

import com.hapifyme.pages.LoginPage;
import io.cucumber.java.en.Given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AuthSteps {
    private final LoginPage loginPage = new LoginPage();
    Properties properties = new Properties();

    @Given("user is logged in")
    public void userIsLoggedIn() throws IOException {
        FileInputStream file =
                new FileInputStream("src/main/resources/config/qa.properties");

        properties.load(file);

        loginPage.openLoginPage();
        loginPage.fillInCredentials(
                properties.getProperty("test.email"),
                properties.getProperty("test.password")
        );

        loginPage.clickLoginButton();
    }
}
