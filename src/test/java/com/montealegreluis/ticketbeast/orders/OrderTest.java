package com.montealegreluis.ticketbeast.orders;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.shared.Uuid;
import org.junit.jupiter.api.Test;

final class OrderTest {
  @Test
  void it_can_be_placed() throws NotEnoughTickets {
    var id = "ac401a00-2f4b-4be1-9b1b-27896335b279";
    var concert = aConcert().withTicketsCount(2).build();
    var quantity = new TicketsQuantity(2);
    var tickets = concert.availableTickets(quantity);
    var order =
        concert.placeOrder(
            Uuid.withValue(id), tickets, Value.email(), concert.priceForTickets(tickets.size()));

    assertNotNull(order);
    assertEquals(Uuid.withValue(id), order.id());
  }

  @Test
  void it_can_be_compared_to_another_order() throws NotEnoughTickets {
    var quantity = new TicketsQuantity(2);
    var concertA =
        aConcert().withId("a6609f9a-85fa-43b9-898c-bd3b34c87191").withTicketsCount(2).build();
    var ticketsA = concertA.availableTickets(quantity);
    var orderA =
        concertA.placeOrder(
            Uuid.withValue("76f3fe0d-4003-4fc4-803a-8801ee804bed"),
            ticketsA,
            Value.email(),
            concertA.priceForTickets(ticketsA.size()));
    var concertB =
        aConcert().withId("9101c9a0-0b8f-4dc1-8600-b4881963803f").withTicketsCount(2).build();
    var ticketsB = concertB.availableTickets(quantity);
    var orderB =
        concertB.placeOrder(
            Uuid.withValue("46425d62-f0ef-465c-930b-0124d98079aa"),
            ticketsB,
            Value.email(),
            concertB.priceForTickets(ticketsB.size()));

    assertEquals(orderA, orderA);
    assertNotEquals(orderA, null);
    assertNotEquals(orderB, orderA);
    assertNotEquals(orderB, new Object());
    assertNotEquals(orderA.hashCode(), new Object().hashCode());
    assertNotEquals(orderA.hashCode(), 0);
  }
}
