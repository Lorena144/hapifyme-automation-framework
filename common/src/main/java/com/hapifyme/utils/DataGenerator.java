package com.hapifyme.utils;

import java.util.UUID;

public class DataGenerator {
    public static String generateRandomFirstName() {
        return "John_" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generateRandomLastName() {
        return "Doe_" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generateRandomEmail() {
        return "test_" + UUID.randomUUID().toString().substring(0, 8) + "@mail.com";
    }

    public static String generateRandomPassword() {
        return "Pass@" + UUID.randomUUID().toString().substring(0, 8);
    }
}
