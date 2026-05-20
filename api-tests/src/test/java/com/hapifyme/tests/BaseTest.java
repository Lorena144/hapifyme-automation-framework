package com.hapifyme.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected Properties properties = new Properties();

    @BeforeClass
    public void setup() throws IOException {

        // Load API environment configuration from properties file
        FileInputStream file =
                new FileInputStream("src/main/resources/config/qa.properties"
                );

        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseUrl");

    }
}
