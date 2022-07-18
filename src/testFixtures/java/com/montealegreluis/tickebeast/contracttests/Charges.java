package com.montealegreluis.tickebeast.contracttests;

import com.montealegreluis.ticketbeast.payments.PaymentToken;

public interface Charges {
  int newChargesCount();

  long lastChargeAmount();

  PaymentToken paymentToken(String creditCardNumber);

  String creditCardNumber();
}
