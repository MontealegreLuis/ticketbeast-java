package com.montealegreluis.tickebeast.builders;

import com.github.javafaker.Faker;

public final class Random {
  private static final Faker faker = new Faker();

  public static String streetAndNumber() {
    return faker.address().streetAddress() + " " + faker.address().streetAddressNumber();
  }

  public static String city() {
    return faker.address().city();
  }

  public static String state() {
    return faker.address().state();
  }

  public static String zipCode() {
    return faker.address().zipCode();
  }

  public static String uuid() {
    return faker.internet().uuid();
  }

  public static int ticketsQuantity() {
    return faker.number().numberBetween(1, 50);
  }

  public static String email() {
    return faker.internet().emailAddress();
  }
}
