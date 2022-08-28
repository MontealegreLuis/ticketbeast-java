package com.montealegreluis.tickebeast.fakes.orders;

import com.montealegreluis.ticketbeast.orders.ConfirmationNumber;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import java.util.ArrayList;
import java.util.List;

public final class InMemoryOrders implements Orders {
  private final List<Order> orders = new ArrayList<>();

  @Override
  public void save(Order order) {
    if (!orders.contains(order)) orders.add(order);
  }

  public Order with(ConfirmationNumber confirmationNumber) throws UnknownOrder {
    return orders.stream()
        .filter(order -> confirmationNumber.equals(order.confirmationNumber()))
        .findFirst()
        .orElseThrow(() -> UnknownOrder.with(confirmationNumber));
  }
}
