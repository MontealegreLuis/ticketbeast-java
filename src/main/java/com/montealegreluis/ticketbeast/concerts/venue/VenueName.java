package com.montealegreluis.ticketbeast.concerts.venue;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class VenueName implements StringValueObject {
  private final String name;

  public VenueName(String name) {
    Assert.notBlank(name, "Venue name cannot be blank or null");
    this.name = name;
  }

  @Override
  public String value() {
    return name;
  }
}
