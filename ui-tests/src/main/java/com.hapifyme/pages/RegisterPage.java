package com.hapifyme.pages;

import com.hapifyme.models.RegisterUser;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private static final Logger logger =
            LoggerFactory.getLogger(RegisterPage.class);

    private final By firstNameInput = By.name("reg_fname");
    private final By lastNameInput = By.name("reg_lname");
    private final By emailInput = By.name("reg_email");
    private final By confirmEmailInput = By.name("reg_email2");
    private final By passwordInput = By.id("reg_password");
    private final By confirmPasswordInput = By.id("reg_password2");
    private final By registerButton = By.name("register_button");
    private final By registrationSuccessMessage = By.className("success-message");

    public void fillRegisterForm(RegisterUser user) {

        logger.info("Filling registration form");
        $(firstNameInput).setValue(user.getFirstName());
        $(lastNameInput).setValue(user.getLastName());
        $(emailInput).setValue(user.getEmail());
        $(confirmEmailInput).setValue(user.getConfirmEmail());
        $(passwordInput).setValue(user.getPassword());
        $(confirmPasswordInput).setValue(user.getConfirmPassword());

    }

    public void clickRegisterButton() {
        logger.info("Clicking register button");
        $(registerButton).click();
    }

    public void verifySuccessfulRegistrationMessage() {
        logger.info("Verifying successful registration message is displayed");
        $(registrationSuccessMessage).shouldBe(visible);
    }
}
