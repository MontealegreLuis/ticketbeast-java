package com.montealegreluis.ticketbeast.values;

import com.montealegreluis.assertions.Assert;
import java.io.Serializable;
import java.util.UUID;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Uuid implements StringValueObject, Serializable {
  private final String identifier;

  public static Uuid generate() {
    return new Uuid(UUID.randomUUID().toString());
  }

  public static Uuid withValue(String identifier) {
    return new Uuid(identifier);
  }

  private Uuid(String identifier) {
    Assert.uuid(identifier);
    this.identifier = identifier;
  }

  @Override
  public String value() {
    return identifier;
  }

  @Override
  public String toString() {
    return identifier;
  }
}
