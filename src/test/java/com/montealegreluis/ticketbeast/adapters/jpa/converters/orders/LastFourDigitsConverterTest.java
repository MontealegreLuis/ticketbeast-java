package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class LastFourDigitsConverterTest {
  @Test
  void it_converts_credit_card_last_4_digits_to_database_value() {
    var digits = "4242";
    var last4Digits = new LastFourDigits(digits);

    assertEquals(digits, converter.convertToDatabaseColumn(last4Digits));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_credit_card_last_4_digits() {
    var digits = "4242";
    var last4Digits = new LastFourDigits(digits);

    assertEquals(last4Digits, converter.convertToEntityAttribute(digits));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new LastFourDigitsConverter();
  }

  private LastFourDigitsConverter converter;
}
