package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class CurrencyCodeTest {
  @Test
  void it_prevents_null_currency_codes() {
    assertThrows(IllegalArgumentException.class, () -> new CurrencyCode(null));
  }

  @Test
  void it_prevents_currency_codes_with_invalid_format() {
    var exception = assertThrows(IllegalArgumentException.class, () -> new CurrencyCode("es"));
    assertEquals("'es' is not a valid currency code", exception.getMessage());
    assertThrows(IllegalArgumentException.class, () -> new CurrencyCode("MX"));
    assertThrows(IllegalArgumentException.class, () -> new CurrencyCode(" "));
  }

  @Test
  void it_knows_its_current_value() {
    var code = "USD";

    var currency = new CurrencyCode(code);

    assertEquals(code, currency.value());
  }
}
