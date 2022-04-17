package com.montealegreluis.tickebeast.adapters.stripe;

import com.montealegreluis.tickebeast.contracttests.Charges;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import io.vavr.control.Try;
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
}
