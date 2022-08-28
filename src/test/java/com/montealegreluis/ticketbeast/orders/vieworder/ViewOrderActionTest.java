package com.montealegreluis.ticketbeast.orders.vieworder;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.tickebeast.fakes.orders.InMemoryOrders;
import com.montealegreluis.tickebeast.fixtures.OrdersFixture;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ViewOrderActionTest {
  @Test
  void it_fails_to_find_an_unknown_order() {
    var input = new ViewOrderInput("2c98d154-8959-4bc2-aa4b-baca5080a352");

    assertThrows(UnknownOrder.class, () -> action.execute(input));
  }

  @Test
  void it_finds_an_existing_order() throws NotEnoughTickets, UnknownOrder {
    var order = scenario.withAnOrderWithTickets(2);
    orders.save(order);
    var input = new ViewOrderInput(order.confirmationNumber().value());

    var existingOrder = action.execute(input);

    assertEquals(existingOrder, order);
  }

  @BeforeEach
  void let() {
    Concerts concerts = new InMemoryConcerts();
    orders = new InMemoryOrders();
    action = new ViewOrderAction(orders);
    scenario = new OrdersFixture(concerts);
  }

  private ViewOrderAction action;

  private OrdersFixture scenario;

  private Orders orders;
}
