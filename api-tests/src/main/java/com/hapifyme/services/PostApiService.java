package com.hapifyme.services;
import com.hapifyme.config.ApiConfig;
import com.hapifyme.context.TestContext;
import com.hapifyme.models.GetPostsResponse;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static io.restassured.RestAssured.given;

public class PostApiService {
    private static final Logger logger =
            LoggerFactory.getLogger(PostApiService.class);

    public void verifyPostExists(String expectedPostMessage){

        ApiConfig.setup();

        logger.info("Fetching user posts from API...");

        GetPostsResponse getPostsResponse =
                given()
                    .header("Authorization", "Bearer " + TestContext.bearerToken)
                    .contentType(ContentType.JSON)
                .when()
                    .get("/user/get_user_posts.php")
                .then()
                    .statusCode(200)
                    .extract()
                    .as(GetPostsResponse.class);


        logger.info("Checking if created post exists in API response...");
        Allure.step("Validating created post through API");

        boolean postExists = getPostsResponse
                .getPosts()
                .stream()
                .anyMatch( post ->
                        post.getBody().contains(expectedPostMessage));

        logger.info("Post exists in API response: {}", postExists);

        if (!postExists) {
            throw new AssertionError("Created post was not found in API response");

        }

    }
}
