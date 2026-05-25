package org.example;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        String name = faker.name().name();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().phoneNumber();
        String address = faker.address().fullAddress();
        String city = faker.address().city();

        System.out.println("name = " + name);
        System.out.println("email = " + email);
        System.out.println("phone = " + phone);
        System.out.println("address = " + address);
        System.out.println("city = " + city);

    }
}

