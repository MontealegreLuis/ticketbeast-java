package com.montealegreluis.tickebeast.fakes.payments;

import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import java.util.ArrayList;
import java.util.List;

public class FakePaymentGateway implements PaymentGateway {
  public static final PaymentToken VALID_TOKEN = new PaymentToken("a-valid-token");
  private final List<Money> charges = new ArrayList<>();

  @Override
  public void charge(Money amount, PaymentToken token) {
    if (!VALID_TOKEN.equals(token)) throw PaymentFailed.forTransactionWith(token);
    charges.add(amount);
  }

  public Money totalCharges() {
    return charges.stream().reduce(Money.of(0, "USD"), Money::add);
  }
}
