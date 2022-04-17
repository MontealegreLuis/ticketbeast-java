package com.montealegreluis.ticketbeast.adapters.stripe;

import static com.montealegreluis.tickebeast.builders.adapters.stripe.TokenBuilder.aToken;

import com.montealegreluis.tickebeast.adapters.stripe.StripeCharges;
import com.montealegreluis.tickebeast.config.StripeConfiguration;
import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.tickebeast.contracttests.PaymentGatewayContractTest;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import com.stripe.net.RequestOptions;
import io.vavr.control.Try;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.temporal.ChronoUnit;
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

  @Override
  protected PaymentToken validToken() {
    var format = new SimpleDateFormat("y");
    var nextYear =
        Integer.parseInt(
            format.format(Date.from(Clock.systemUTC().instant().plus(365, ChronoUnit.DAYS))));
    String token =
        Try.of(
                () ->
                    aToken()
                        .withRequestOptions(requestOptions)
                        .withCreditCardYear(nextYear)
                        .build()
                        .getId())
            .get();
    return new PaymentToken(token);
  }

  private RequestOptions requestOptions;
  @Autowired private StripeConfiguration stripe;
}
