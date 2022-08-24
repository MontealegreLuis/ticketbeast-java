package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.assertions.Assert.notBlank;

import com.montealegreluis.ticketbeast.shared.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Code implements StringValueObject {
  private final String code;

  public Code(final String code) {
    notBlank(code, "Code cannot be blank or null");
    this.code = code;
  }

  @Override
  public String value() {
    return code;
  }
}
