package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class CodeTest {
  @Test
  void it_cannot_be_blank_or_null() {
    assertThrows(IllegalArgumentException.class, () -> new Code(null));
    assertThrows(IllegalArgumentException.class, () -> new Code(""));
    assertThrows(IllegalArgumentException.class, () -> new Code("      "));
  }

  @Test
  void it_knows_its_current_value() {
    var codeValue = "ABCDEF";

    var code = new Code(codeValue);

    assertEquals(codeValue, code.value());
  }
}
