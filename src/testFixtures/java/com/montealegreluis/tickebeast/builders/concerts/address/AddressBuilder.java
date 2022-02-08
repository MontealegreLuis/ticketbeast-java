package com.montealegreluis.tickebeast.builders.concerts.address;

import com.montealegreluis.tickebeast.builders.Random;
import com.montealegreluis.ticketbeast.concerts.address.*;

public final class AddressBuilder {
  private String zipCode = Random.zipCode();

  public static AddressBuilder anAddress() {
    return new AddressBuilder();
  }

  public AddressBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public Address build() {
    return new Address(
        new StreetAndNumber(Random.streetAndNumber()),
        new City(Random.city()),
        new State(Random.state()),
        new ZipCode(zipCode));
  }
}
