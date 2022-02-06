package com.montealegreluis.tickebeast.builders.concerts.address;

import com.github.javafaker.Faker;
import com.montealegreluis.ticketbeast.concerts.address.*;

public final class AddressBuilder {
  public static final Faker faker = new Faker();
  private String zipCode = faker.address().zipCode();

  public static AddressBuilder anAddress() {
    return new AddressBuilder();
  }

  public AddressBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public Address build() {
    String streetAndNumber =
        faker.address().streetAddress() + " " + faker.address().streetAddressNumber();
    return new Address(
        new StreetAndNumber(streetAndNumber),
        new City(faker.address().city()),
        new State(faker.address().state()),
        new ZipCode(zipCode));
  }
}
