package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.shared.Uuid;
import org.junit.jupiter.api.Test;

final class TicketTest {
  @Test
  void it_can_be_compared_to_another_ticket() {
    var ticketA =
        Ticket.forConcert(
            Uuid.withValue("e67ddcd8-b717-400b-8419-87b0a2aacb6a"), aConcert().build());
    var ticketB =
        Ticket.forConcert(
            Uuid.withValue("1be1052a-fe79-429d-bb56-4c35adf83ca7"), aConcert().build());

    assertEquals(ticketA, ticketA);
    assertNotEquals(ticketA, null);
    assertNotEquals(ticketB, ticketA);
    assertNotEquals(ticketB, new Object());
    assertNotEquals(ticketA.hashCode(), new Object().hashCode());
    assertNotEquals(ticketA.hashCode(), 0);
  }
}
