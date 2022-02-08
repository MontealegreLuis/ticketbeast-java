package com.montealegreluis.ticketbeast.values;

import java.util.UUID;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Uuid implements StringValueObject {
  private final String identifier;

  public static Uuid generate() {
    return new Uuid(UUID.randomUUID().toString());
  }

  public static Uuid withValue(String identifier) {
    return new Uuid(UUID.fromString(identifier).toString());
  }

  private Uuid(String identifier) {
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
