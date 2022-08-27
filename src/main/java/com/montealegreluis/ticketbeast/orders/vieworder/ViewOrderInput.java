package com.montealegreluis.ticketbeast.orders.vieworder;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.ticketbeast.shared.Uuid;

public class ViewOrderInput implements Query {
  private final Uuid orderId;

  public ViewOrderInput(String orderId) {
    this.orderId = Uuid.withValue(orderId);
  }

  public Uuid orderId() {
    return orderId;
  }
}
