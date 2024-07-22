package com.selenium.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    private static String firstName;
    private static String lastName;

    public static String generateName() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        return firstName + " " + lastName;
    }

    public static String generateEmail() {
        if (firstName == null || lastName == null) {
            generateName();
        }
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateAddress() {
        return faker.address().fullAddress();
    }
}
