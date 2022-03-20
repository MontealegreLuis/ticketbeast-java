package com.montealegreluis.ticketbeast.orders;

import static com.montealegreluis.tickebeast.builders.orders.OrderBuilder.anOrder;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class OrderTest {
  @Test
  void it_can_be_placed() {
    var id = "ac401a00-2f4b-4be1-9b1b-27896335b279";
    var order = anOrder().withId(id).withTicketsQuantity(2).build();

    assertNotNull(order);
    assertEquals(Uuid.withValue(id), order.id());
    assertEquals(2, order.ticketsCount());
    var tickets = new ArrayList<>(order.getTickets());
    assertEquals(order, tickets.get(0).getOrder());
    assertEquals(order, tickets.get(1).getOrder());
  }

  @Test
  void it_can_be_compared_to_another_order() {
    var orderA = anOrder().withId("76f3fe0d-4003-4fc4-803a-8801ee804bed").build();
    var orderB = anOrder().withId("46425d62-f0ef-465c-930b-0124d98079aa").build();

    assertEquals(orderA, orderA);
    assertNotEquals(orderA, null);
    assertNotEquals(orderB, orderA);
    assertNotEquals(orderB, new Object());
    assertNotEquals(orderA.hashCode(), new Object().hashCode());
    assertNotEquals(orderA.hashCode(), 0);
  }
}
