package com.montealegreluis.ticketbeast.values;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.servicebuses.domainevents.Identifier;
import java.util.UUID;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Uuid implements Identifier, StringValueObject {
  private String identifier;

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
