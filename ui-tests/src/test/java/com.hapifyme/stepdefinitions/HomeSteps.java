package com.hapifyme.stepdefinitions;

import com.hapifyme.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps {
    HomePage homePage = new HomePage();
    private String postMessage;

    @When("user enters a post message {string}")
    public void userEntersPostMessage(String message) {
        postMessage = homePage.enterPostMessage(message);
    }

    @And("user clicks the Post button")
    public void userClickPostButton() {
        homePage.clickPostButton();
    }

    @Then("the post should be displayed in the posts area")
    public void verifyPostsAreaIsDisplayed() {
        homePage.verifyPostIsDisplayed(postMessage);
    }

}
