package com.montealegreluis.ticketbeast.orders;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.adapters.hashids.HashIdsCodesGenerator;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

final class OrderTest {
  @Test
  void it_can_be_placed() throws NotEnoughTickets {
    var id = Uuid.withValue("ac401a00-2f4b-4be1-9b1b-27896335b279");
    var email = Value.email();
    var concert = aConcert().withTicketsCount(2).build();
    var quantity = new TicketsQuantity(2);
    var reservation = concert.reserveTickets(quantity, email);
    var confirmationNumber = Value.confirmationNumber();
    var charge = new ProcessedCharge(reservation.total(), new LastFourDigits("4242"));
    var order =
        reservation.complete(id, confirmationNumber, charge, new HashIdsCodesGenerator("a salt"));

    assertNotNull(order);
    assertEquals(id, order.id());
    var tickets = new ArrayList<>(reservation.tickets());
    assertEquals(2, tickets.size());
    assertFalse(tickets.get(0).isAvailable());
    assertFalse(tickets.get(1).isAvailable());
  }

  @Test
  void it_can_be_compared_to_another_order() throws NotEnoughTickets {
    var generator = new HashIdsCodesGenerator("a salt");
    var quantity = new TicketsQuantity(2);
    var concertA =
        aConcert().withId("a6609f9a-85fa-43b9-898c-bd3b34c87191").withTicketsCount(2).build();
    var reservationA = concertA.reserveTickets(quantity, Value.email());
    var chargeA = new ProcessedCharge(reservationA.total(), new LastFourDigits("4242"));
    var orderA =
        reservationA.complete(
            Uuid.withValue("76f3fe0d-4003-4fc4-803a-8801ee804bed"),
            Value.confirmationNumber(),
            chargeA,
            generator);
    var concertB =
        aConcert().withId("9101c9a0-0b8f-4dc1-8600-b4881963803f").withTicketsCount(2).build();
    var reservationB = concertB.reserveTickets(quantity, Value.email());
    var chargeB = new ProcessedCharge(reservationA.total(), new LastFourDigits("2727"));
    var orderB =
        reservationB.complete(
            Uuid.withValue("46425d62-f0ef-465c-930b-0124d98079aa"),
            Value.confirmationNumber(),
            chargeB,
            generator);

    assertEquals(orderA, orderA);
    assertNotEquals(orderA, null);
    assertNotEquals(orderB, orderA);
    assertNotEquals(orderB, new Object());
    assertNotEquals(orderA.hashCode(), new Object().hashCode());
    assertNotEquals(orderA.hashCode(), 0);
  }
}
