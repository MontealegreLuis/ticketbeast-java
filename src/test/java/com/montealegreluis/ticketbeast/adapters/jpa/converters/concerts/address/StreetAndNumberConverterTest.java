package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.address.StreetAndNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class StreetAndNumberConverterTest {
  @Test
  void it_converts_street_and_number_to_database_value() {
    var streetAndNumberValue = "Main St. 123";
    var streetAndNumber = new StreetAndNumber(streetAndNumberValue);

    assertEquals(streetAndNumberValue, converter.convertToDatabaseColumn(streetAndNumber));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_street_and_number() {
    var streetAndNumberValue = "Main St. 123";
    var streetAndNumber = new StreetAndNumber(streetAndNumberValue);

    assertEquals(streetAndNumber, converter.convertToEntityAttribute(streetAndNumberValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new StreetAndNumberConverter();
  }

  private StreetAndNumberConverter converter;
}
