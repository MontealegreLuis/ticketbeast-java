package com.montealegreluis.tickebeast.fakes.orders;

import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class InMemoryOrders implements Orders {
  private final List<Order> orders = new ArrayList<>();

  @Override
  public void save(Order order) {
    if (!orders.contains(order)) orders.add(order);
  }

  public Optional<Order> withId(Uuid id) {
    return orders.stream().filter(order -> id.equals(order.id())).findFirst();
  }
}
