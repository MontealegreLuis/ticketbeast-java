package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CurrencyCodeConverterTest {
  @Test
  void it_converts_currency_code_to_database_value() {
    var code = "USD";
    var currencyCode = new CurrencyCode(code);

    assertEquals(code, converter.convertToDatabaseColumn(currencyCode));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_currency_code() {
    var code = "USD";
    var currencyCode = new CurrencyCode(code);

    assertEquals(currencyCode, converter.convertToEntityAttribute(code));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new CurrencyCodeConverter();
  }

  private CurrencyCodeConverter converter;
}
