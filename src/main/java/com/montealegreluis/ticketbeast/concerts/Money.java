package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.CurrencyCodeConverter;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Money {
  private long amount;

  @Convert(converter = CurrencyCodeConverter.class)
  private CurrencyCode currency;

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
