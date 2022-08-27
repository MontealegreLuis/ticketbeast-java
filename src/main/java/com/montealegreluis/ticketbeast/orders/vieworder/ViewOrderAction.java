package com.montealegreluis.ticketbeast.orders.vieworder;

import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;

public final class ViewOrderAction implements QueryHandler<ViewOrderInput, Order> {
  private final Orders orders;

  public ViewOrderAction(Orders orders) {
    this.orders = orders;
  }

  @Override
  public Order execute(ViewOrderInput input) throws UnknownOrder {
    return orders.withId(input.orderId());
  }
}
