package com.montealegreluis.tickebeast.fakes.orders;

import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.ArrayList;
import java.util.List;

public final class InMemoryOrders implements Orders {
  private final List<Order> orders = new ArrayList<>();

  @Override
  public void save(Order order) {
    if (!orders.contains(order)) orders.add(order);
  }

  public Order withId(Uuid orderId) throws UnknownOrder {
    return orders.stream()
        .filter(order -> orderId.equals(order.id()))
        .findFirst()
        .orElseThrow(() -> UnknownOrder.withId(orderId));
  }
}
