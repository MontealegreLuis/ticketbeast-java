package com.montealegreluis.tickebeast.fakes.payments;

import com.montealegreluis.tickebeast.contracttests.Charges;

public final class InMemoryCharges implements Charges {
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
}
