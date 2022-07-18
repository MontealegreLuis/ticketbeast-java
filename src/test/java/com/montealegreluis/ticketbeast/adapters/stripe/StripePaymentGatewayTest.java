package com.montealegreluis.ticketbeast.adapters.stripe;

import com.montealegreluis.tickebeast.adapters.stripe.StripeCharges;
import com.montealegreluis.tickebeast.config.StripeConfiguration;
import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.tickebeast.contracttests.PaymentGatewayContractTest;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.stripe.net.RequestOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Tag("slow")
@ActiveProfiles("test")
@SpringBootTest(classes = {StripeConfiguration.class})
final class StripePaymentGatewayTest extends PaymentGatewayContractTest {
  @BeforeEach
  void let() {
    requestOptions = RequestOptions.builder().setApiKey(stripe.getApiKey()).build();
  }

  @Override
  protected PaymentGateway payments() {
    return new StripePaymentGateway(requestOptions);
  }

  @Override
  protected Charges charges() {
    var charges = new StripeCharges(requestOptions);
    charges.lastChargeAmount();
    return charges;
  }

  private RequestOptions requestOptions;
  @Autowired private StripeConfiguration stripe;
}
