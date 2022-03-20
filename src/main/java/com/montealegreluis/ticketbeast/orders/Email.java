package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.values.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Email implements StringValueObject {
  private final String email;

  public Email(String email) {
    Assert.notBlank(email, "Email cannot be blank or null");
    Assert.email(email);
    this.email = email;
  }

  @Override
  public String value() {
    return email;
  }
}
