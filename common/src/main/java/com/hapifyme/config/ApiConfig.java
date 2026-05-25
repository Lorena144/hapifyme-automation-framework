package com.hapifyme.config;

import io.restassured.RestAssured;

public class ApiConfig {

    public static void setup() {

        RestAssured.baseURI =
                "https://apps.qualiadept.eu/hapifyme/api";
    }

}
