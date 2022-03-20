package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class PaymentToken implements StringValueObject {
  private final String token;

  public PaymentToken(String token) {
    Assert.notBlank(token, "Payment token cannot be null or blank");
    this.token = token;
  }

  @Override
  public String value() {
    return token;
  }
}
