package com.montealegreluis.tickebeast.fakes.payments;

import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.payments.*;

public final class FakePaymentGateway implements PaymentGateway {
  private final InMemoryCharges charges;

  public FakePaymentGateway(InMemoryCharges charges) {
    this.charges = charges;
  }

  @Override
  public ProcessedCharge charge(final Money amount, final PaymentToken token) throws PaymentFailed {
    if (!charges.has(token)) throw PaymentFailed.forTransactionWith(token);

    charges.increaseChargesCount();
    charges.setLastChargeAmount(amount.getAmount());

    final String creditCardNumber = charges.cardUsedWith(token);
    final LastFourDigits cardLast4Digits =
        new LastFourDigits(creditCardNumber.substring(creditCardNumber.length() - 4));

    return new ProcessedCharge(amount, cardLast4Digits);
  }
}
