package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class ReservationTest {
  @Test
  void it_reserves_tickets() throws NotEnoughTickets {
    var concert = aConcert().withTicketsCount(3).withTicketPrice(Money.of(2000, "USD")).build();
    var reservation = concert.reserveTickets(new TicketsQuantity(2));

    assertEquals(2, reservation.tickets().size());
    assertFalse(new ArrayList<>(reservation.tickets()).get(0).isAvailable());
    assertFalse(new ArrayList<>(reservation.tickets()).get(1).isAvailable());
    assertEquals(Money.of(4000, "USD"), reservation.cost());
  }
}
