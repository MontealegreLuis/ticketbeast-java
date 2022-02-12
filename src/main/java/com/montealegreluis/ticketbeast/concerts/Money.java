package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;

public final class Money {
  private final long amount;
  private final CurrencyCode currency;

  public static Money of(long cents, String currency) {
    return new Money(cents, new CurrencyCode(currency));
  }

  public long amount() {
    return amount;
  }

  public String currencyCode() {
    return currency.value();
  }

  private Money(long amount, CurrencyCode currency) {
    Assert.min(amount, 0, "Money amount cannot be less than zero. '%s' given.");
    this.amount = amount;
    this.currency = currency;
  }
}
