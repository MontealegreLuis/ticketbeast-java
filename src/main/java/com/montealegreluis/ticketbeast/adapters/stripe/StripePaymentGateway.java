package com.montealegreluis.ticketbeast.adapters.stripe;

import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import io.vavr.control.Try;
import java.util.HashMap;
import java.util.Map;

public final class StripePaymentGateway implements PaymentGateway {
  private final RequestOptions requestOptions;

  public StripePaymentGateway(final RequestOptions requestOptions) {
    this.requestOptions = requestOptions;
  }

  @Override
  public void charge(final Money amount, final PaymentToken token) throws PaymentFailed {
    final Map<String, Object> params = new HashMap<>();
    params.put("amount", amount.getAmount());
    params.put("currency", amount.getCurrency().value());
    params.put("source", token.value());
    Try.run(() -> Charge.create(params, requestOptions))
        .getOrElseThrow((e) -> PaymentFailed.forTransactionWith(token, e));
  }
}
