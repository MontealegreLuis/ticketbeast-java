package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.assertions.Assert;
import com.montealegreluis.ticketbeast.shared.StringValueObject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class ConfirmationNumber implements StringValueObject {
  private static final String POOL = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
  private final String confirmationNumber;

  public static ConfirmationNumber generate() {
    List<String> collect =
        POOL.repeat(24).chars().mapToObj(Character::toString).collect(Collectors.toList());
    Collections.shuffle(collect);
    return new ConfirmationNumber(String.join("", collect.subList(0, 24)));
  }

  public ConfirmationNumber(String confirmationNumber) {
    Assert.notBlank(confirmationNumber);
    this.confirmationNumber = confirmationNumber;
  }

  @Override
  public String value() {
    return confirmationNumber;
  }
}
