package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class TicketsQuantityTest {
  @Test
  void its_minimum_value_is_one() {
    assertThrows(IllegalArgumentException.class, () -> new TicketsQuantity(0));
  }

  @Test
  void it_knows_its_current_value() {
    var quantity = 5;
    var ticketsQuantity = new TicketsQuantity(quantity);

    assertEquals(quantity, ticketsQuantity.value());
  }
}
