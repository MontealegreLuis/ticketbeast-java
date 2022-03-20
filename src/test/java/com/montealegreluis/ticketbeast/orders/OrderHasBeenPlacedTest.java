package com.montealegreluis.ticketbeast.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import org.junit.jupiter.api.Test;

final class OrderHasBeenPlacedTest {
  @Test
  void it_knows_its_name_and_aggregate_id() {
    var orderId = Value.id();
    var event = new OrderHasBeenPlaced(orderId, Value.email(), Value.id(), 4);

    assertEquals("concerts.orderHasBeenPlaced", event.name());
    assertEquals(orderId.value(), event.aggregateId());
  }
}
