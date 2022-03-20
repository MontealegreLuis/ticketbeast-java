package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.concerts.Money;

public interface PaymentGateway {
  void charge(Money amount, PaymentToken token);
}
