package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.Subtitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class SubtitleConverterTest {
  @Test
  void it_converts_subtitle_to_database_value() {
    var subtitleValue = "with Animosity and the Lethargy";
    var subtitle = new Subtitle(subtitleValue);

    assertEquals(subtitleValue, converter.convertToDatabaseColumn(subtitle));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_subtitle() {
    var subtitleValue = "with Animosity and the Lethargy";
    var subtitle = new Subtitle(subtitleValue);

    assertEquals(subtitle, converter.convertToEntityAttribute(subtitleValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new SubtitleConverter();
  }

  private SubtitleConverter converter;
}
