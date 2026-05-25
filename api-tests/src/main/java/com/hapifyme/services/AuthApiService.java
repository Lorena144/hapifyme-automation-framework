package com.hapifyme.services;
import com.hapifyme.config.ApiConfig;
import com.hapifyme.context.TestContext;
import com.hapifyme.models.LoginRequest;
import com.hapifyme.models.LoginResponse;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static io.restassured.RestAssured.given;

public class AuthApiService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthApiService.class);

    public void loginUser(String username, String password){

        ApiConfig.setup();

        logger.info("Logging in through API...");
        Allure.step("Authenticating through API and retrieving bearer token");

        LoginRequest loginRequest = new LoginRequest(username, password);

        LoginResponse loginResponse =
                given()
                        .body(loginRequest)
                        .contentType(ContentType.JSON)
                .when()
                        .post("/user/login.php")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        TestContext.bearerToken = loginResponse.getToken();
        Allure.addAttachment(
                "Bearer Token Status",
                TestContext.bearerToken != null ? "Generated" : "Missing");

    }
}
