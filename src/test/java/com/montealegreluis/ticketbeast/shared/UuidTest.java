package com.montealegreluis.ticketbeast.shared;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
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

  @Test
  void it_knows_its_current_value() {
    var uuid = "e3da4d44-4ed1-40c1-ad02-61d32b77a3eb";
    var id = Uuid.withValue(uuid);

    assertEquals(uuid, id.value());
    assertEquals(uuid, id.toString());
  }
}
