package com.montealegreluis.ticketbeast.payments;

import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.tickebeast.contracttests.PaymentGatewayContractTest;
import com.montealegreluis.tickebeast.fakes.payments.FakePaymentGateway;
import com.montealegreluis.tickebeast.fakes.payments.InMemoryCharges;

final class FakePaymentGatewayTest extends PaymentGatewayContractTest {
  @Override
  protected Charges charges() {
    charges = new InMemoryCharges();
    return charges;
  }

  @Override
  protected PaymentGateway payments() {
    return new FakePaymentGateway((InMemoryCharges) charges);
  }

  private Charges charges;
}
