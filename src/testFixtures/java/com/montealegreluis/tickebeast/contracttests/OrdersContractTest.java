package com.montealegreluis.tickebeast.contracttests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.fixtures.OrdersFixture;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import org.junit.jupiter.api.Test;

public abstract class OrdersContractTest {
  @Test
  void it_finds_an_existing_concert() throws NotEnoughTickets, UnknownOrder {
    var order = scenario().withAnOrderWithTickets(2);
    var orders = orders();
    orders.save(order);

    var existingOrder = orders.withId(order.id());

    assertEquals(existingOrder, order);
  }

  @Test
  void it_fails_to_find_an_unknown_concert() {
    var orders = orders();
    var unknownOrderId = Value.id();

    assertThrows(UnknownOrder.class, () -> orders.withId(unknownOrderId));
  }

  public abstract Orders orders();

  public abstract OrdersFixture scenario();
}
