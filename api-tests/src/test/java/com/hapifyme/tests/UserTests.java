package com.hapifyme.tests;

import com.hapifyme.context.TestContext;
import com.hapifyme.models.UpdateUserProfileRequest;
import com.hapifyme.models.UserProfileResponse;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

@Epic("API Automation")
@Feature("User Profile")
public class UserTests extends BaseTest {

    @Story("Get user profile")
    @Description("Verify that a user profile can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "com.hapifyme.tests.AuthTests.loginUserSuccessfully")
    public void getUserProfile(){

        UserProfileResponse profileResponse =
                given()
                        .filter(new AllureRestAssured())
                        .header("Authorization", TestContext.apiKey)
                        .contentType(ContentType.JSON)
                        .queryParam("user_id", TestContext.userId)
                .when()
                        .get("/user/get_profile.php")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(UserProfileResponse.class);

        validateUserProfile(profileResponse);
        validateUserData(profileResponse);

    }

    @Step("Validate user profile response")
    public void validateUserProfile(UserProfileResponse response) {
        Assert.assertEquals(response.getStatus(), "success");
        Assert.assertNotNull(response.getUser());
    }

    @Step("Validate user profile data")
    public void validateUserData(UserProfileResponse response) {
        Assert.assertEquals(response.getUser().getEmail(), TestContext.email);
        Assert.assertEquals(response.getUser().getUsername(), TestContext.username);
        Assert.assertEquals(response.getUser().getFirstName(), TestContext.firstName);
        Assert.assertEquals(response.getUser().getLastName(), TestContext.lastName);
    }

    @Story("Update user profile")
    @Description("Verify that a user profile can be updated")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "getUserProfile")
    public void updateUserProfile(){

        String firstNameUpdated = TestContext.firstName + "_updated";

        UpdateUserProfileRequest updateUserProfileRequest =  new UpdateUserProfileRequest(
                TestContext.userId,
                firstNameUpdated,
                TestContext.lastName,
                TestContext.email, "default_profile_pic.png");

            given()
                    .filter(new AllureRestAssured())
                    .header("Authorization", TestContext.apiKey)
                    .contentType(ContentType.JSON)
                    .body(updateUserProfileRequest)
            .when()
                    .put("/user/update_profile.php")
            .then()
                    .statusCode(200)
                    .body("status", equalTo("success"));

        Allure.addAttachment("Updated first name", firstNameUpdated);

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

        validateUserDataUpdate(updatedProfileResponse, firstNameUpdated);
        TestContext.firstName = firstNameUpdated;

    }

    @Step("Validate updated user profile data")
    public void validateUserDataUpdate(UserProfileResponse response, String expectedFirstName) {
        Assert.assertEquals(response.getStatus(), "success");
        Assert.assertEquals(response.getUser().getFirstName(), expectedFirstName);

    }

    @Story("Delete user")
    @Description("Verify that a user can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "updateUserProfile")
    public void deleteUserProfile(){

        Response deleteResponse = given()
                    .filter(new AllureRestAssured())
                    .header("Authorization", "Bearer " + TestContext.bearerToken)
                    .contentType(ContentType.JSON)
                .when()
                    .delete("/user/delete_profile.php")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        validateDeletedUserResponse(deleteResponse.jsonPath().getString("status"));
        Allure.addAttachment("Deleted user ID", TestContext.userId);

        Response deletedProfileResponse = given()
                    .header("Authorization", TestContext.apiKey)
                    .contentType(ContentType.JSON)
                    .queryParam("user_id", TestContext.userId)
                .when()
                    .get("/user/get_profile.php")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        validateDeletedProfileResponse(
                deletedProfileResponse.jsonPath().getString("status"),
                deletedProfileResponse.jsonPath().getString("message"));
    }

    @Step("Validate deleted user response")
    public void validateDeletedUserResponse(String status) {
        Assert.assertEquals(status, "success");

    }

    @Step("Validate profile response after delete")
    public void validateDeletedProfileResponse(String status, String message) {
        Assert.assertEquals(status, "error");
        Assert.assertEquals(message, "User not found.");

    }
}
