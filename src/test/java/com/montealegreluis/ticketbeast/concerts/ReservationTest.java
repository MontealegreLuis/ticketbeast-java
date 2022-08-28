package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.adapters.hashids.HashIdsCodesGenerator;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class ReservationTest {
  @Test
  void it_reserves_tickets() throws NotEnoughTickets {
    var concert = aConcert().withTicketsCount(3).withTicketPrice(Money.of(2000, "USD")).build();
    var email = Value.email();
    var reservation = concert.reserveTickets(new TicketsQuantity(2), email, generator);

    assertEquals(2, reservation.tickets().size());
    assertFalse(new ArrayList<>(reservation.tickets()).get(0).isAvailable());
    assertFalse(new ArrayList<>(reservation.tickets()).get(1).isAvailable());
    assertEquals(email, reservation.email());
    assertEquals(Money.of(4000, "USD"), reservation.total());
  }

  @Test
  void it_generates_different_codes_for_different_reservations() throws NotEnoughTickets {
    var concert = aConcert().withTicketsCount(3).withTicketPrice(Money.of(2000, "USD")).build();

    var reservationA = concert.reserveTickets(new TicketsQuantity(2), Value.email(), generator);
    var reservationB = concert.reserveTickets(new TicketsQuantity(1), Value.email(), generator);

    assertEquals(2, reservationA.tickets().size());
    assertEquals(
        generator.generateCodeFor(1L), new ArrayList<>(reservationA.tickets()).get(0).getCode());
    assertEquals(
        generator.generateCodeFor(2L), new ArrayList<>(reservationA.tickets()).get(1).getCode());
    assertEquals(1, reservationB.tickets().size());
    assertEquals(
        generator.generateCodeFor(3L), new ArrayList<>(reservationB.tickets()).get(0).getCode());
  }

  private static final CodesGenerator generator = new HashIdsCodesGenerator("a salt");
}
