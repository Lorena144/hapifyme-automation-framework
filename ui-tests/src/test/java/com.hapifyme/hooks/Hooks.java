package com.hapifyme.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks{
    private final Properties properties = new Properties();
    private static final Logger logger =
            LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup() throws IOException {

        logger.info("Initializing browser configuration");

        // Load framework configuration from properties file
        FileInputStream file =
                new FileInputStream("src/main/resources/config/qa.properties");

        properties.load(file);

        Configuration.browser = properties.getProperty("browser");
        Configuration.baseUrl = properties.getProperty("baseUrl");
        Configuration.browserSize = properties.getProperty("browserSize");

        Configuration.headless = true;
    }

    @After
    public void teardown() {
        logger.info("Closing web driver");
        closeWebDriver();
    }
}
