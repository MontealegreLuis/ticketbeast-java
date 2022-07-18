package com.montealegreluis.ticketbeast.orders;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.tickebeast.fakes.orders.InMemoryOrders;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryOrdersTest {
  @Test
  void it_saves_an_order() throws NotEnoughTickets {
    var quantity = new TicketsQuantity(2);
    var concert = aConcert().withTicketsCount(quantity.value()).build();
    concerts.save(concert);
    var orderId = Value.id();
    var reservation = concert.reserveTickets(quantity, Value.email());
    var confirmationNumber = Value.confirmationNumber();
    var charge = new ProcessedCharge(reservation.total(), new LastFourDigits("4242"));
    var order = reservation.complete(orderId, confirmationNumber, charge);

    orders.save(order);

    var savedOrder = orders.withId(orderId);
    assertTrue(savedOrder.isPresent());
    assertEquals(order, savedOrder.get());
  }

  @BeforeEach
  void let() {
    concerts = new InMemoryConcerts();
    orders = new InMemoryOrders();
  }

  private InMemoryOrders orders;
  private Concerts concerts;
}
