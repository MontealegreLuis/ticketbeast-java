package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.address.ZipCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ZipCodeConverterTest {
  @Test
  void it_converts_zip_code_to_database_value() {
    var zipCodeValue = "72000";
    var zipCode = new ZipCode(zipCodeValue);

    assertEquals(zipCodeValue, converter.convertToDatabaseColumn(zipCode));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_zip_code() {
    var zipCodeValue = "72000";
    var zipCode = new ZipCode(zipCodeValue);

    assertEquals(zipCode, converter.convertToEntityAttribute(zipCodeValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new ZipCodeConverter();
  }

  private ZipCodeConverter converter;
}
