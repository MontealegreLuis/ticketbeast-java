package com.montealegreluis.tickebeast.contracttests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import org.junit.jupiter.api.Test;

public abstract class PaymentGatewayContractTest {
  @Test
  void it_completes_a_charge_with_a_valid_token() throws PaymentFailed {
    var charges = charges();
    var payments = payments();
    var token = validToken();
    var amount = Value.amount();

    payments.charge(amount, token);

    assertEquals(1, charges.newChargesCount());
    assertEquals(charges.lastChargeAmount(), amount.getAmount());
  }

  @Test
  void it_fails_to_complete_a_charge_with_an_invalid_token() {
    var charges = charges();
    var payments = payments();
    var invalidToken = new PaymentToken("invalid token");

    assertThrows(PaymentFailed.class, () -> payments.charge(Value.amount(), invalidToken));

    assertEquals(0, charges.newChargesCount());
  }

  protected abstract PaymentToken validToken();

  protected abstract Charges charges();

  protected abstract PaymentGateway payments();
}
