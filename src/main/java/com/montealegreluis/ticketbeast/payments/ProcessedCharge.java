package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.concerts.Money;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class ProcessedCharge {
  private final Money amount;
  private final LastFourDigits cardLast4Digits;

  public ProcessedCharge(Money amount, LastFourDigits cardLast4Digits) {
    this.amount = amount;
    this.cardLast4Digits = cardLast4Digits;
  }

  public Money amount() {
    return amount;
  }

  public LastFourDigits cardLast4Digits() {
    return cardLast4Digits;
  }
}
