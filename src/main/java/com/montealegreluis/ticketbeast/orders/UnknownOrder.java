package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.ticketbeast.shared.UnknownAggregate;
import com.montealegreluis.ticketbeast.shared.Uuid;

public class UnknownOrder extends UnknownAggregate {
  public static UnknownOrder withId(Uuid orderId) {
    return new UnknownOrder("Cannot find order with ID " + orderId);
  }

  protected UnknownOrder(String message) {
    super(message);
  }
}
