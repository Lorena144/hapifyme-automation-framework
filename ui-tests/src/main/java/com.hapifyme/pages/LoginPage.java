package com.hapifyme.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final Logger logger =
            LoggerFactory.getLogger(LoginPage.class);

    private final By emailInput = By.id("emailId");
    private final By passwordInput = By.id("passwordId");
    private final By loginButton = By.name("login_button");
    private final By errorMessage = By.id("log_inv");
    private final By postArea = By.className("post_form");
    private final By signUpButton = By.id("signup");

    public void openLoginPage() {
        logger.info("Opening login page");
        open("/login_register.php");
    }

    public void setEmail(String email) {
        $(emailInput).setValue(email);
    }

    public void setPassword(String password) {
        $(passwordInput).setValue(password);
    }

    public void clickLoginButton() {
        logger.info("Clicking login button");
        $(loginButton).click();
    }

    public void verifyUserIsRedirectedToHomePage() {
        logger.info("Verifying user is redirected to homepage");
        $(postArea).shouldBe(visible);
    }

    public void verifyErrorMessageIsDisplayed(String expectedMessage) {
        $(errorMessage).shouldHave(text(expectedMessage));
    }

    public void navigateToRegisterPage() {
        $(signUpButton).click();
    }

    public void fillInCredentials(String email, String password) {
        logger.info("Entering login credentials");
        $(emailInput).setValue(email);
        $(passwordInput).setValue(password);
    }
}
