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
}
