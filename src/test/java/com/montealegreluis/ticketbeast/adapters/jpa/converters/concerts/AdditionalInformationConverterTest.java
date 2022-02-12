package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.AdditionalInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class AdditionalInformationConverterTest {
  @Test
  void it_converts_additional_information_to_database_value() {
    var information = "For tickets call (555) 222-2222.";
    var additionalInformation = new AdditionalInformation(information);

    assertEquals(information, converter.convertToDatabaseColumn(additionalInformation));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_additional_information() {
    var information = "For tickets call (555) 222-2222.";
    var additionalInformation = new AdditionalInformation(information);

    assertEquals(additionalInformation, converter.convertToEntityAttribute(information));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new AdditionalInformationConverter();
  }

  private AdditionalInformationConverter converter;
}
