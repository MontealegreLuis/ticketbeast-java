package com.montealegreluis.ticketbeast.adapters.jpa.repositories.orders;

import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import com.montealegreluis.ticketbeast.shared.Uuid;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository implements Orders {
  private final OrdersJpaRepository orders;

  public OrdersRepository(OrdersJpaRepository orders) {
    this.orders = orders;
  }

  @Override
  public void save(Order order) {
    orders.save(order);
  }

  @Override
  public Order withId(Uuid orderId) throws UnknownOrder {
    return orders.findById(orderId).orElseThrow(() -> UnknownOrder.withId(orderId));
  }
}
