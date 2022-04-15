package com.montealegreluis.ticketbeast.adapters.stripe;

import static com.montealegreluis.tickebeast.builders.adapters.stripe.TokenBuilder.aToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.config.StripeConfiguration;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {StripeConfiguration.class})
final class StripePaymentGatewayTest {
  @Test
  void it_completes_a_charge_with_a_valid_token() throws StripeException, PaymentFailed {
    var token = aToken().withRequestOptions(requestOptions).withCreditCardYear(nextYear).build();
    var amount = Value.amount();

    payments.charge(amount, new PaymentToken(token.getId()));

    assertEquals(1, newCharges().size());
    assertEquals(lastCharge().getAmount(), amount.getAmount());
  }

  @Test
  void it_fails_to_complete_a_charge_with_an_invalid_token() throws StripeException {
    var invalidToken = new PaymentToken("invalid token");

    assertThrows(PaymentFailed.class, () -> payments.charge(Value.amount(), invalidToken));

    assertEquals(0, newCharges().size());
  }

  @BeforeEach
  void let() throws StripeException {
    nextYear =
        Integer.parseInt(
            format.format(Date.from(Clock.systemUTC().instant().plus(365, ChronoUnit.DAYS))));
    requestOptions = RequestOptions.builder().setApiKey(stripe.getApiKey()).build();
    payments = new StripePaymentGateway(requestOptions);
    lastCharge = lastCharge();
  }

  private Charge lastCharge() throws StripeException {
    final Map<String, Object> params = new HashMap<>();
    params.put("limit", 1);
    return Charge.list(params, requestOptions).getData().get(0);
  }

  private List<Charge> newCharges() throws StripeException {
    final Map<String, Object> params = new HashMap<>();
    params.put("limit", 1);
    params.put("ending_before", lastCharge.getId());
    return Charge.list(params, requestOptions).getData();
  }

  private int nextYear;
  private Charge lastCharge;
  private RequestOptions requestOptions;
  private StripePaymentGateway payments;
  @Autowired private StripeConfiguration stripe;
  private static final SimpleDateFormat format = new SimpleDateFormat("y");
}
