package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.ticketbeast.shared.Uuid;

public interface Orders {
  void save(Order order);

  Order withId(Uuid orderId) throws UnknownOrder;
}
