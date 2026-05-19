package com.hapifyme.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private static final Logger logger =
            LoggerFactory.getLogger(HomePage.class);

    private final By postMessageInput = By.id("post_text");
    private final By postButton = By.id("post_button");
    private final By loadingGif = By.id("loading");
    private final By postsArea = By.className("posts_area");

    public String enterPostMessage(String postText) {
        logger.info("Entering post message");

        // Generate unique post content to avoid conflicts between test runs
        String uniquePost =
                postText + System.currentTimeMillis();

        $(postMessageInput).setValue(uniquePost);

        return uniquePost;
    }

    public void clickPostButton() {
        logger.info("Clicking post button");
        $(postButton).click();
    }

    public void waitForLoadingGifToDisappear() {
        logger.info("Waiting for loading spinner to disappear");
        $(loadingGif).should(disappear, Duration.ofSeconds(10));
    }

    public void verifyPostIsDisplayed(String postText) {
        logger.info("Verifying post is displayed on home page");
        waitForLoadingGifToDisappear();
        $(postsArea).shouldHave(text(postText));
    }
}
