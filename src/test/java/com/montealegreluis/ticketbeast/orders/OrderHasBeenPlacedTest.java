package com.montealegreluis.ticketbeast.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.servicebuses.domainevents.Identifier;
import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.List;
import org.junit.jupiter.api.Test;

final class OrderHasBeenPlacedTest {
  @Test
  void it_knows_its_name_and_aggregate_id() {
    var orderId = Value.id();
    var ticketIds = List.of((Identifier) Value.id());
    var event =
        new OrderHasBeenPlaced(orderId, Value.confirmationNumber(), Value.email(), ticketIds);

    assertEquals("concerts.orderHasBeenPlaced", event.name());
    assertEquals(orderId.value(), event.aggregateId());
  }

  @Test
  void it_can_be_compared_to_another_event() {
    var eventA =
        new OrderHasBeenPlaced(
            Uuid.withValue("eb704aa8-e44d-4298-ad76-ccbbb99f7e17"),
            Value.confirmationNumber(),
            new Email("example@eacmple.com"),
            List.of(Uuid.withValue("7c162753-df55-4c5e-ae4a-a5e948ed39e6")));
    var eventB =
        new OrderHasBeenPlaced(
            Uuid.withValue("94adc39f-c712-4273-b5ce-3cb59b4cff54"),
            Value.confirmationNumber(),
            new Email("example@eacmple.com"),
            List.of(Uuid.withValue("2388f223-56da-43ac-8d88-673003693aa0")));

    assertEquals(eventA, eventA);
    assertNotEquals(eventA, null);
    assertNotEquals(eventA, eventB);
    assertNotEquals(eventA, new Object());
    assertEquals(eventA.hashCode(), eventA.hashCode());
    assertNotEquals(eventA.hashCode(), new Object().hashCode());
    assertNotEquals(eventA.hashCode(), 0);
  }
}
