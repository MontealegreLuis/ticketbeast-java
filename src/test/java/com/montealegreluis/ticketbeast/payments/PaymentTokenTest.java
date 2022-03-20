package com.montealegreluis.ticketbeast.payments;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import com.montealegreluis.tickebeast.builders.Random;
import org.junit.jupiter.api.Test;

final class PaymentTokenTest {
  @Test
  void it_cannot_be_blank_or_null() {
    assertThrows(IllegalArgumentException.class, () -> new PaymentToken(null));
    assertThrows(IllegalArgumentException.class, () -> new PaymentToken(" "));
  }

  @Test
  void it_knows_its_current_value() {
    String paymentToken = Random.uuid();
    var token = new PaymentToken(paymentToken);

    assertEquals(paymentToken, token.value());
  }
}
