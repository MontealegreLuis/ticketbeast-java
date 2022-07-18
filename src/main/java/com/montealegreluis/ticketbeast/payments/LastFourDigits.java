package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.shared.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class LastFourDigits implements StringValueObject {
  private final String lastFourDigits;

  public LastFourDigits(String lastFourDigits) {
    Assert.notNull(lastFourDigits, "Credit card last 4 digits cannot be null");
    Assert.pattern(lastFourDigits, "^[0-9]{4}$", "'%s' are not valid credit card last 4 digits");
    this.lastFourDigits = lastFourDigits;
  }

  @Override
  public String value() {
    return lastFourDigits;
  }
}
