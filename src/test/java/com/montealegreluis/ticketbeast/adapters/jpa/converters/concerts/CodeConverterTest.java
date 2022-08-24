package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CodeConverterTest {
  @Test
  void it_converts_code_to_database_value() {
    var codeValue = "ABDCEF";
    var code = new Code(codeValue);

    assertEquals(codeValue, converter.convertToDatabaseColumn(code));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_code() {
    var codeValue = "ABDCEF";
    var code = new Code(codeValue);

    assertEquals(code, converter.convertToEntityAttribute(codeValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new CodeConverter();
  }

  private CodeConverter converter;
}
