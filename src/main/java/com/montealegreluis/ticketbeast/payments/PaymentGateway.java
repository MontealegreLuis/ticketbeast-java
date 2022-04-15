package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.concerts.Money;

public interface PaymentGateway {
  void charge(final Money amount, final PaymentToken token) throws PaymentFailed;
}
