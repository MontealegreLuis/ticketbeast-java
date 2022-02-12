package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class TitleConverterTest {
  @Test
  void it_converts_title_to_database_value() {
    var titleValue = "The red chord";
    var title = new Title(titleValue);

    assertEquals(titleValue, converter.convertToDatabaseColumn(title));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_title() {
    var titleValue = "The red chord";
    var title = new Title(titleValue);

    assertEquals(title, converter.convertToEntityAttribute(titleValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new TitleConverter();
  }

  private TitleConverter converter;
}
