package com.hapifyme.stepdefinitions;

import com.hapifyme.pages.HomePage;
import com.hapifyme.services.PostApiService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.hapifyme.context.TestContext;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @When("user enters a post message {string}")
    public void userEntersPostMessage(String message) {
        TestContext.postMessage = message;
        homePage.enterPostMessage(message);
    }

    @And("user clicks the Post button")
    public void userClickPostButton() {
        homePage.clickPostButton();
    }

    @Then("the post should be displayed in the posts area")
    public void verifyPostsAreaIsDisplayed() {
        homePage.verifyPostIsDisplayed(TestContext.postMessage);
    }

    @Then("the created post should exist in API response")
    public void createdPostShouldExistInApiResponse() {

        PostApiService postApiService = new PostApiService();
        postApiService.verifyPostExists(TestContext.postMessage);
    }

}
