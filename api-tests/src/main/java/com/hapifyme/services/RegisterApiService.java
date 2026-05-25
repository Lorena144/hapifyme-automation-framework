package com.hapifyme.services;

import com.hapifyme.config.ApiConfig;
import com.hapifyme.context.TestContext;
import com.hapifyme.models.RegisterRequest;
import com.hapifyme.models.RegisterResponse;
import com.hapifyme.utils.DataGenerator;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import com.hapifyme.utils.ApiWaitUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static io.restassured.RestAssured.given;

public class RegisterApiService {

    private final static Logger logger = LoggerFactory.getLogger(RegisterApiService.class);

    public void registerUser() {

        TestContext.firstName = DataGenerator.generateRandomFirstName();
        TestContext.lastName = DataGenerator.generateRandomLastName();
        TestContext.email = DataGenerator.generateRandomEmail();
        TestContext.password = DataGenerator.generateRandomPassword();

        logger.info("Creating user account via API...");
        Allure.step("Creating user account through API...");

        ApiConfig.setup();

        RegisterRequest registerRequest =
                new RegisterRequest(
                        TestContext.firstName,
                        TestContext.lastName,
                        TestContext.email,
                        TestContext.password
                );

        Allure.addAttachment(
                "Generated User",
                "Email: " + TestContext.email);

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

        logger.info("User created successfully with email: {}", TestContext.email);

        TestContext.apiKey = registerResponse.getApiKey();
        TestContext.userId = registerResponse.getUserId();
        TestContext.username = registerResponse.getUsername();
        TestContext.confirmationToken = registerResponse.getConfirmationToken();

        String statusUrl =
                "/user/retrieve_token.php?username_or_email="
                        + TestContext.username;

        ApiWaitUtils.waitForStatus(
                statusUrl,
                "success",
                TestContext.apiKey,
                200
        );
    }
}
