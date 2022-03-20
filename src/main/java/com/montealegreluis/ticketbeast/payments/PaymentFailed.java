package com.montealegreluis.ticketbeast.payments;

public final class PaymentFailed extends RuntimeException {
  public static PaymentFailed forTransactionWith(PaymentToken token) {
    return new PaymentFailed("Payment with token '" + token.value() + "' cannot be completed");
  }

  private PaymentFailed(String message) {
    super(message);
  }
}
