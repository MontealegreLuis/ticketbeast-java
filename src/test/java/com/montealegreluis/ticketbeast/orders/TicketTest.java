package com.montealegreluis.ticketbeast.orders;

import static com.montealegreluis.tickebeast.builders.orders.OrderBuilder.anOrder;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.values.Uuid;
import org.junit.jupiter.api.Test;

final class TicketTest {
  @Test
  void it_can_be_created_for_an_order() {
    var ticket = Ticket.forOrder(Value.id(), anOrder().build());

    assertNotNull(ticket);
  }

  @Test
  void it_can_be_compared_to_another_order() {
    var ticketA =
        Ticket.forOrder(Uuid.withValue("e67ddcd8-b717-400b-8419-87b0a2aacb6a"), anOrder().build());
    var ticketB =
        Ticket.forOrder(Uuid.withValue("1be1052a-fe79-429d-bb56-4c35adf83ca7"), anOrder().build());

    assertEquals(ticketA, ticketA);
    assertNotEquals(ticketA, null);
    assertNotEquals(ticketB, ticketA);
    assertNotEquals(ticketB, new Object());
    assertNotEquals(ticketA.hashCode(), new Object().hashCode());
    assertNotEquals(ticketA.hashCode(), 0);
  }
}
