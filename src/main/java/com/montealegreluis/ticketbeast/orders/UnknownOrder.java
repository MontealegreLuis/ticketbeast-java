package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.ticketbeast.shared.UnknownAggregate;

public class UnknownOrder extends UnknownAggregate {
  public static UnknownOrder with(ConfirmationNumber confirmationNumber) {
    return new UnknownOrder("Cannot find order with confirmation number " + confirmationNumber);
  }

  protected UnknownOrder(String message) {
    super(message);
  }
}
