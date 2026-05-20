package com.hapifyme.tests;

import com.hapifyme.context.TestContext;
import com.hapifyme.models.UpdateUserProfileRequest;
import com.hapifyme.models.UserProfileResponse;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

public class UserTests extends BaseTest {

    @Test(dependsOnMethods = "com.hapifyme.tests.AuthTests.registerUser")
    public void getUserProfile(){

        UserProfileResponse profileResponse =
                given()
                        .header("Authorization", TestContext.apiKey)
                        .contentType(ContentType.JSON)
                        .queryParam("user_id", TestContext.userId)
                .when()
                        .get("/user/get_profile.php")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(UserProfileResponse.class);

        String extractedEmail = profileResponse.getUser().getEmail();
        String extractedUsername = profileResponse.getUser().getUsername();
        String extractedLastName = profileResponse.getUser().getLastName();
        String extractedFirstName = profileResponse.getUser().getFirstName();

        Assert.assertEquals(extractedEmail, TestContext.email);
        Assert.assertEquals(extractedUsername, TestContext.username);
        Assert.assertEquals(extractedFirstName, TestContext.firstName);
        Assert.assertEquals(extractedLastName, TestContext.lastName);

        Assert.assertEquals(profileResponse.getStatus(), "success");
        Assert.assertNotNull(profileResponse.getUser());

    }

    @Test(dependsOnMethods = "com.hapifyme.tests.AuthTests.registerUser")
    public void updateUserProfile(){

        String firstNameUpdated = TestContext.firstName + "_updated";

        UpdateUserProfileRequest updateUserProfileRequest =  new UpdateUserProfileRequest(
                TestContext.userId,
                firstNameUpdated,
                TestContext.lastName,
                TestContext.email, "default_profile_pic.png");

            given()
                    .header("Authorization", TestContext.apiKey)
                    .contentType(ContentType.JSON)
                    .body(updateUserProfileRequest)
            .when()
                    .put("/user/update_profile.php")
            .then()
                    .statusCode(200)
                    .body("status", equalTo("success"));


        UserProfileResponse updatedProfileResponse =
                given()
                        .header("Authorization", TestContext.apiKey)
                        .contentType(ContentType.JSON)
                        .queryParam("user_id", TestContext.userId)
                .when()
                        .get("/user/get_profile.php")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(UserProfileResponse.class);

        Assert.assertEquals(updatedProfileResponse.getStatus(), "success");
        Assert.assertEquals(updatedProfileResponse.getUser().getFirstName(), firstNameUpdated);
        TestContext.firstName = firstNameUpdated;

    }

    @Test(dependsOnMethods = "com.hapifyme.tests.AuthTests.loginUserSuccessfully")
    public void deleteUserProfile(){

        given()
                .header("Authorization", "Bearer " + TestContext.bearerToken)
                .contentType(ContentType.JSON)
        .when()
                .delete("/user/delete_profile.php")
        .then()
                .statusCode(200)
                .body("status", equalTo("success"));


        given()
                .header("Authorization", TestContext.apiKey)
                .contentType(ContentType.JSON)
                .queryParam("user_id", TestContext.userId)
        .when()
                .get("/user/get_profile.php")
        .then()
                .statusCode(200)
                .body("status", equalTo("error"))
                .body("message", equalTo("User not found."));
    }
}
