package com.hapifyme.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

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

        await()
                .alias("Waiting for expected API status")
                .atMost(20, SECONDS)
                .pollInterval(2, SECONDS)

                .untilAsserted(() -> {

                    given()
                            .header("Authorization", apiKey)

                    .when()
                            .get(url)

                    .then()
                            .statusCode(expectedStatusCode)
                            .body("status", equalTo(expectedStatus));
                });

        logger.info("Expected status confirmed");
    }
}
