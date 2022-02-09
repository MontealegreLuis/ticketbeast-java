package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;

public final class Subtitle implements StringValueObject {
  private final String subtitle;

  public Subtitle(String subtitle) {
    Assert.notBlank(subtitle, "Concert subtitle cannot be empty or null");
    this.subtitle = subtitle;
  }

  @Override
  public String value() {
    return subtitle;
  }
}
