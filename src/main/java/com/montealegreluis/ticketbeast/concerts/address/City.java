package com.montealegreluis.ticketbeast.concerts.address;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;

public final class City implements StringValueObject {
  private final String city;

  public City(String city) {
    Assert.notBlank(city, "City name cannot be blank or null");
    this.city = city;
  }

  @Override
  public String value() {
    return city;
  }
}
