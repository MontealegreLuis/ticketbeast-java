package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.shared.IntegerValueObject;

public final class TicketsQuantity implements IntegerValueObject {
  private final int quantity;

  public TicketsQuantity(int quantity) {
    Assert.min(quantity, 1, "Quantity must be greater than zero");
    this.quantity = quantity;
  }

  @Override
  public Integer value() {
    return quantity;
  }
}
