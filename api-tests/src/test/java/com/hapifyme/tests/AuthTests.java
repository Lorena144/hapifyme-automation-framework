package com.hapifyme.tests;

import com.hapifyme.context.TestContext;
import com.hapifyme.models.LoginRequest;
import com.hapifyme.models.LoginResponse;
import com.hapifyme.models.RegisterRequest;
import com.hapifyme.models.RegisterResponse;
import com.hapifyme.utils.DataGenerator;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.hapifyme.utils.ApiWaitUtils;

import static io.restassured.RestAssured.given;

public class AuthTests extends BaseTest{

    @Test
    public void registerUser(){

        TestContext.firstName = DataGenerator.generateRandomFirstName();
        TestContext.lastName = DataGenerator.generateRandomLastName();
        TestContext.email = DataGenerator.generateRandomEmail();
        TestContext.password = DataGenerator.generateRandomPassword();

        RegisterRequest registerRequest = new RegisterRequest(TestContext.firstName, TestContext.lastName, TestContext.email, TestContext.password);

        RegisterResponse registerResponse =
                given()
                        .contentType(ContentType.JSON)
                        .body(registerRequest)
                .when()
                        .post("/user/register.php")
                .then()
                        .statusCode(201)
                        .extract()
                        .as(RegisterResponse.class);

        Assert.assertEquals(registerResponse.getStatus(), "success");
        Assert.assertNotNull(registerResponse.getUserId());
        Assert.assertNotNull(registerResponse.getApiKey());

        TestContext.apiKey = registerResponse.getApiKey();
        TestContext.userId = registerResponse.getUserId();
        TestContext.username = registerResponse.getUsername();
        TestContext.confirmationToken = registerResponse.getConfirmationToken();

        String statusurl = "/user/retrieve_token.php?username_or_email=" + TestContext.username;
        ApiWaitUtils.waitForStatus(statusurl, "success", TestContext.apiKey, 200);
    }

    @Test(dependsOnMethods = "registerUser")
    public void loginUserSuccessfully(){

        LoginRequest loginRequest = new LoginRequest(TestContext.username, TestContext.password);

        LoginResponse loginResponse =
                given()
                        .contentType(ContentType.JSON)
                        .body(loginRequest)
                .when()
                        .post("/user/login.php")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        Assert.assertEquals(loginResponse.getStatus(), "success");
        Assert.assertNotNull(loginResponse.getToken());

        TestContext.bearerToken = loginResponse.getToken();
    }

    @Test(dependsOnMethods = "registerUser")
    public void loginUserUnsuccessfully(){

        LoginRequest loginRequest = new LoginRequest(TestContext.username, "WrongPass!1");

        given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
        .when()
                .post("/user/login.php")
        .then()
                .statusCode(401);
    }
}
