package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Title implements StringValueObject {
  private final String title;

  public Title(String title) {
    Assert.notBlank(title, "Concert title cannot be blank or null");
    this.title = title;
  }

  @Override
  public String value() {
    return title;
  }
}
