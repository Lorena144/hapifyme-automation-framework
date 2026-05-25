package com.hapifyme.utils;

import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ApiWaitUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(ApiWaitUtils.class);


    /**
     * Waits until an authenticated API endpoint returns
     * the expected status value.
     *
     * @param url                The endpoint URL to poll
     * @param expectedStatus     The expected value of the "status" field
     * @param apiKey             The authorization API key
     * @param expectedStatusCode The expected HTTP status code
     */
    public static void waitForStatus(String url,
                                     String expectedStatus,
                                     String apiKey,
                                     int expectedStatusCode) {

        logger.info(
                "Polling endpoint {} expecting status {}",
                url,
                expectedStatus
        );

        Awaitility.await()
                .alias("Waiting for expected API status")
                .atMost(20, SECONDS)
                .pollInterval(2, SECONDS)

                .untilAsserted(() -> {

                    RestAssured.given()
                            .header("Authorization", apiKey)

                    .when()
                            .get(url)

                    .then()
                            .statusCode(expectedStatusCode)
                            .body("status", Matchers.equalTo(expectedStatus));
                });

        logger.info("Expected status confirmed");
    }
}
