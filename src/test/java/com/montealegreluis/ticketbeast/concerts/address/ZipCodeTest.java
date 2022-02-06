package com.montealegreluis.ticketbeast.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class ZipCodeTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new ZipCode(" "));
    assertThrows(IllegalArgumentException.class, () -> new ZipCode(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedZipCode = "72000";

    var zipCode = new ZipCode(expectedZipCode);

    assertEquals(expectedZipCode, zipCode.value());
  }
}
