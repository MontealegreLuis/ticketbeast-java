package com.montealegreluis.tickebeast.adapters.stripe;

import static com.montealegreluis.tickebeast.builders.adapters.stripe.TokenBuilder.aToken;

import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import io.vavr.control.Try;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public final class StripeCharges implements Charges {
  private final RequestOptions requestOptions;
  private Charge lastCharge;

  public StripeCharges(RequestOptions requestOptions) {
    this.requestOptions = requestOptions;
  }

  @Override
  public int newChargesCount() {
    final Map<String, Object> params = new HashMap<>();
    params.put("limit", 1);
    params.put("ending_before", lastCharge.getId());
    return Try.of(() -> Charge.list(params, requestOptions).getData().size()).get();
  }

  @Override
  public long lastChargeAmount() {
    final Map<String, Object> params = new HashMap<>();
    params.put("limit", 1);
    lastCharge = Try.of(() -> Charge.list(params, requestOptions).getData().get(0)).get();
    return lastCharge.getAmount();
  }

  @Override
  public PaymentToken paymentToken(String creditCardNumber) {
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
                        .withCreditCardNumber(creditCardNumber)
                        .build()
                        .getId())
            .get();

    return new PaymentToken(token);
  }

  public String creditCardNumber() {
    return "4242424242424242";
  }
}
