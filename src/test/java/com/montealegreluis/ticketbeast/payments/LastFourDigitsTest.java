package com.montealegreluis.ticketbeast.payments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class LastFourDigitsTest {
  @Test
  void it_knows_its_current_value() {
    var last4Digits = new LastFourDigits("1907");
    assertEquals("1907", last4Digits.value());
  }

  @Test
  void it_prevents_invalid_credit_card_4_last_digits() {
    assertThrows(IllegalArgumentException.class, () -> new LastFourDigits(null));
    assertThrows(IllegalArgumentException.class, () -> new LastFourDigits("123"));
    assertThrows(IllegalArgumentException.class, () -> new LastFourDigits("12345"));
    assertThrows(IllegalArgumentException.class, () -> new LastFourDigits("1Ax4"));
  }
}
