package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class AdditionalInformationTest {
  @Test
  void it_prevents_blank_or_null_additional_information() {
    assertThrows(IllegalArgumentException.class, () -> new AdditionalInformation(" "));
    assertThrows(IllegalArgumentException.class, () -> new AdditionalInformation(null));
  }

  @Test
  void it_knows_its_current_value() {
    var information = "Show will start right on time";

    var additionalInformation = new AdditionalInformation(information);

    assertEquals(information, additionalInformation.value());
  }
}
