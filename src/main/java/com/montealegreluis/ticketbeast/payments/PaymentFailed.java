package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.servicebuses.DomainException;

public final class PaymentFailed extends DomainException {
  public static PaymentFailed forTransactionWith(PaymentToken token) {
    return new PaymentFailed("Payment with token '" + token.value() + "' cannot be completed");
  }

  private PaymentFailed(String message) {
    super(message);
  }
}
