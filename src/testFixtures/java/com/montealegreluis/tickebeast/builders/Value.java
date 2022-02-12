package com.montealegreluis.tickebeast.builders;

import com.github.javafaker.Faker;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class Value {
  private static final Faker faker = new Faker();

  public static Date dateInFutureDays(int atMostDays) {
    return faker.date().future(atMostDays, TimeUnit.DAYS);
  }

  public static Uuid id() {
    return Uuid.generate();
  }
}
