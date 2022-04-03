package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.shared.InvalidAction;

public final class PaymentFailed extends InvalidAction {
  public static PaymentFailed forTransactionWith(PaymentToken token) {
    return new PaymentFailed("Payment with token '" + token.value() + "' cannot be completed");
  }

  private PaymentFailed(String message) {
    super(message);
  }
}
