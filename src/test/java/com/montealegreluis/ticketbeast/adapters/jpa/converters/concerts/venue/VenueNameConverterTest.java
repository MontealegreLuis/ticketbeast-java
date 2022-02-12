package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.venue;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.venue.VenueName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class VenueNameConverterTest {
  @Test
  void it_converts_venue_name_to_database_value() {
    var venueNameValue = "The Mosh Pit";
    var venueName = new VenueName(venueNameValue);

    assertEquals(venueNameValue, converter.convertToDatabaseColumn(venueName));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_venue_name() {
    var venueNameValue = "The Mosh Pit";
    var venueName = new VenueName(venueNameValue);

    assertEquals(venueName, converter.convertToEntityAttribute(venueNameValue));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new VenueNameConverter();
  }

  private VenueNameConverter converter;
}
