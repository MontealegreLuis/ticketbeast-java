package com.montealegreluis.ticketbeast.values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class UuidTest {
  @Test
  void it_can_be_created_from_existing_value() {
    String identifier = "9d185d78-dd33-43ce-8dc2-22b4b8d3ead0";

    var uuid = Uuid.withValue(identifier);

    assertEquals(identifier, uuid.value());
  }

  @Test
  void it_cannot_be_created_from_invalid_value() {
    var invalidUUID = "not an uuid";

    assertThrows(IllegalArgumentException.class, () -> Uuid.withValue(invalidUUID));
  }
}
