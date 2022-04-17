package com.montealegreluis.tickebeast.fakes.payments;

import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.PaymentToken;

public final class FakePaymentGateway implements PaymentGateway {
  public static final PaymentToken VALID_TOKEN = new PaymentToken("a-valid-token");
  private final InMemoryCharges charges;

  public FakePaymentGateway(InMemoryCharges charges) {
    this.charges = charges;
  }

  @Override
  public void charge(final Money amount, final PaymentToken token) throws PaymentFailed {
    if (!VALID_TOKEN.equals(token)) throw PaymentFailed.forTransactionWith(token);

    charges.increaseChargesCount();
    charges.setLastChargeAmount(amount.getAmount());
  }
}
