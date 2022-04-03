package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.shared.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class CurrencyCode implements StringValueObject {
  private final String code;

  public CurrencyCode(String code) {
    Assert.notNull(code, "Currency code cannot be null");
    Assert.pattern(code, "[A-Z]{3}", "'%s' is not a valid currency code");
    this.code = code;
  }

  @Override
  public String value() {
    return code;
  }
}
