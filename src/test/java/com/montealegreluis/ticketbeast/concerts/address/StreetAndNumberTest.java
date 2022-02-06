package com.montealegreluis.ticketbeast.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class StreetAndNumberTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new StreetAndNumber(" "));
    assertThrows(IllegalArgumentException.class, () -> new StreetAndNumber(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedStreetAndNumber = "Main St. 12134";

    var streetAndNumber = new StreetAndNumber(expectedStreetAndNumber);

    assertEquals(expectedStreetAndNumber, streetAndNumber.value());
  }
}
