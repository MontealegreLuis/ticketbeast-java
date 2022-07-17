package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.orders.ConfirmationNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ConfirmationNumberConverterTest {
  @Test
  void it_converts_confirmation_number_to_database_value() {
    var confirmationNumber = "J9Q3TXCWSVJRBNG5WM23DUHA";

    assertEquals(
        confirmationNumber,
        converter.convertToDatabaseColumn(new ConfirmationNumber(confirmationNumber)));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_confirmation_number() {
    var confirmationNumber = new ConfirmationNumber("J9Q3TXCWSVJRBNG5WM23DUHA");

    assertEquals(
        confirmationNumber, converter.convertToEntityAttribute("J9Q3TXCWSVJRBNG5WM23DUHA"));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new ConfirmationNumberConverter();
  }

  private ConfirmationNumberConverter converter;
}
