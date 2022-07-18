package com.montealegreluis.tickebeast.fakes.payments;

import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InMemoryCharges implements Charges {
  private static final PaymentToken VALID_TOKEN = new PaymentToken("a-valid-token");
  private final Map<PaymentToken, String> tokens = new LinkedHashMap<>();
  private int chargesCount = 0;
  private long lastChargeAmount = 0;

  public void increaseChargesCount() {
    chargesCount += 1;
  }

  public void setLastChargeAmount(long lastChargeAmount) {
    this.lastChargeAmount = lastChargeAmount;
  }

  @Override
  public int newChargesCount() {
    return chargesCount;
  }

  @Override
  public long lastChargeAmount() {
    return lastChargeAmount;
  }

  @Override
  public PaymentToken paymentToken(String creditCardNumber) {
    tokens.put(VALID_TOKEN, creditCardNumber);
    return VALID_TOKEN;
  }

  public boolean has(PaymentToken token) {
    return tokens.containsKey(token);
  }

  public String cardUsedWith(PaymentToken token) {
    return tokens.get(token);
  }

  @Override
  public String creditCardNumber() {
    return "4242424242424242";
  }
}
