package com.montealegreluis.ticketbeast.adapters.jpa.repositories.orders;

import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
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
}
