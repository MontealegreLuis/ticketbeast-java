package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.ticketbeast.concerts.Money;

public interface PaymentGateway {
  ProcessedCharge charge(final Money amount, final PaymentToken token) throws PaymentFailed;
}
