package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.CurrencyCodeConverter;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Money {
  private long amount;

  @Convert(converter = CurrencyCodeConverter.class)
  private CurrencyCode currency;

  public static Money of(long cents, String currency) {
    return new Money(cents, new CurrencyCode(currency));
  }

  private Money(long amount, CurrencyCode currency) {
    Assert.min(amount, 0L, "Money amount cannot be less than zero. '%s' given.");
    this.amount = amount;
    this.currency = currency;
  }

  public Money multiply(int multiplier) {
    return new Money(amount * multiplier, currency);
  }

  public Money add(Money anotherMoney) {
    Assert.isTrue(
        currency.equals(anotherMoney.currency),
        String.format(
            "Currencies must be identical. '%s' and '%s' given", currency, anotherMoney.currency));

    return Money.of(amount + anotherMoney.amount, "USD");
  }

  @Override
  public String toString() {
    return String.format("%.2f %s", (float) amount / 100, currency.value());
  }
}
