package com.montealegreluis.tickebeast.contracttests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.payments.*;
import org.junit.jupiter.api.Test;

public abstract class PaymentGatewayContractTest {
  @Test
  void it_completes_a_charge_with_a_valid_token() throws PaymentFailed {
    var charges = charges();
    var payments = payments();
    var creditCardNumber = charges.creditCardNumber();
    var cardLast4Digits =
        new LastFourDigits(creditCardNumber.substring(creditCardNumber.length() - 4));
    var token = charges.paymentToken(creditCardNumber);
    var amount = Value.amount();

    var charge = payments.charge(amount, token);

    assertEquals(1, charges.newChargesCount());
    assertEquals(charges.lastChargeAmount(), amount.getAmount());
    assertEquals(new ProcessedCharge(amount, cardLast4Digits), charge);
  }

  @Test
  void it_fails_to_complete_a_charge_with_an_invalid_token() {
    var charges = charges();
    var payments = payments();
    var invalidToken = new PaymentToken("invalid token");

    assertThrows(PaymentFailed.class, () -> payments.charge(Value.amount(), invalidToken));

    assertEquals(0, charges.newChargesCount());
  }

  protected abstract Charges charges();

  protected abstract PaymentGateway payments();
}
