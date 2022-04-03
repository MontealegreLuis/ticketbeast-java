package com.montealegreluis.ticketbeast.concerts.address;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.shared.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class State implements StringValueObject {
  private final String state;

  public State(String state) {
    Assert.notBlank(state, "State cannot be blank or null");
    this.state = state;
  }

  @Override
  public String value() {
    return state;
  }
}
