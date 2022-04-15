package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.shared.InvalidAction;

public final class PaymentFailed extends InvalidAction {
  public static PaymentFailed forTransactionWith(PaymentToken token) {
    return new PaymentFailed("Payment with token '" + token.value() + "' cannot be completed");
  }

  public static PaymentFailed forTransactionWith(PaymentToken token, Throwable cause) {
    return new PaymentFailed(
        "Payment with token '" + token.value() + "' cannot be completed. " + cause.getMessage(),
        cause);
  }

  public PaymentFailed(String message, Throwable cause) {
    super(message, cause);
  }

  private PaymentFailed(String message) {
    super(message);
  }
}
