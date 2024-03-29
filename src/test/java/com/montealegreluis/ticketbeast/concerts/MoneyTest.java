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

    assertEquals(amount, money.getAmount());
    assertEquals(dollars, money.getCurrency().value());
  }

  @Test
  void it_can_be_multiplied() {
    var twoDollars = Money.of(200, "USD");

    var fourDollars = twoDollars.multiply(2);

    assertEquals(Money.of(400, "USD"), fourDollars);
  }

  @Test
  void it_prevents_adding_2_amounts_with_different_currency() {
    var twoDollars = Money.of(200, "USD");
    var twoPesos = Money.of(200, "MXN");

    assertThrows(IllegalArgumentException.class, () -> twoPesos.add(twoDollars));
  }

  @Test
  void it_has_a_text_representation() {
    var money = Money.of(3325, "USD");

    assertEquals("33.25 USD", money.toString());
  }
}
