package com.montealegreluis.ticketbeast.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class EmailTest {
  @Test
  void it_cannot_be_blank_or_empty() {
    assertThrows(IllegalArgumentException.class, () -> new Email(" "));
    assertThrows(IllegalArgumentException.class, () -> new Email(null));
  }

  @Test
  void it_prevents_invalid_emails() {
    assertThrows(IllegalArgumentException.class, () -> new Email("this is not an email"));
  }

  @Test
  void it_knows_its_current_value() {
    var address = "jane.doe@example.com.mx";
    var email = new Email(address);

    assertEquals(address, email.value());
  }
}
