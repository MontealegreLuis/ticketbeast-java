package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;

public final class AdditionalInformation implements StringValueObject {
  private final String additionalInformation;

  public AdditionalInformation(String additionalInformation) {
    Assert.notBlank(additionalInformation, "Additional information cannot be blank or null");
    this.additionalInformation = additionalInformation;
  }

  @Override
  public String value() {
    return additionalInformation;
  }
}
