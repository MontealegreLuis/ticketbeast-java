package com.montealegreluis.ticketbeast.concerts.address;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;

public final class ZipCode implements StringValueObject {
  private final String zipCode;

  public ZipCode(String zipCode) {
    Assert.notBlank(zipCode, "Zip code cannot be blank or null");
    this.zipCode = zipCode;
  }

  @Override
  public String value() {
    return zipCode;
  }
}
