package com.montealegreluis.ticketbeast.concerts.address;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class StreetAndNumber implements StringValueObject {
  private final String streetAndNumber;

  public StreetAndNumber(String streetAndNumber) {
    Assert.notBlank(streetAndNumber, "Street and number cannot be blank or null");
    this.streetAndNumber = streetAndNumber;
  }

  @Override
  public String value() {
    return streetAndNumber;
  }
}
