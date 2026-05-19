package com.hapifyme.utils;

public class TestDataGenerator {

    public static String generateUniqueEmail(String email) {

        long timestamp = System.currentTimeMillis();

        return email.replace("@", "_" + timestamp + "@");
    }
}
