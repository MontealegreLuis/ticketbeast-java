package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class MoneyTest {
  @Test
  void it_cannot_have_a_negative_amount() {
    assertThrows(IllegalArgumentException.class, () -> Money.of(-1, "USD"));
  }

  @Test
  void it_cannot_have_an_invalid_currency_code() {
    assertThrows(IllegalArgumentException.class, () -> Money.of(0, "MX"));
    assertThrows(IllegalArgumentException.class, () -> Money.of(0, null));
  }

  @Test
  void it_knows_its_current_amount_and_currency() {
    var dollars = "USD";
    var amount = 10;

    var money = Money.of(amount, dollars);

    assertEquals(amount, money.amount());
    assertEquals(dollars, money.currencyCode());
  }
}
