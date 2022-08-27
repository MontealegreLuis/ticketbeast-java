package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.tickebeast.contracttests.OrdersContractTest;
import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.tickebeast.fakes.orders.InMemoryOrders;
import com.montealegreluis.tickebeast.fixtures.OrdersFixture;
import com.montealegreluis.ticketbeast.concerts.*;

final class InMemoryOrdersTest extends OrdersContractTest {
  @Override
  public Orders orders() {
    return new InMemoryOrders();
  }

  @Override
  public OrdersFixture scenario() {
    return new OrdersFixture(new InMemoryConcerts());
  }
}
