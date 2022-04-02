package com.montealegreluis.tickebeast.builders;

import com.github.javafaker.Faker;
import com.montealegreluis.ticketbeast.concerts.AdditionalInformation;
import com.montealegreluis.ticketbeast.concerts.Subtitle;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.concerts.Title;
import com.montealegreluis.ticketbeast.orders.Email;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Value {
  private static final List<String> titles =
      List.of("The red chord", "The Cranberries", "The Cors", "Paramore");
  private static final List<String> subtitles =
      List.of(
          "with Animosity and the Lethargy", "with Matchbox 20", "with Creed", "with New Radicals");
  private static final Faker faker = new Faker();

  public static Date dateInFutureDays(int atMostDays) {
    return faker.date().future(atMostDays, TimeUnit.DAYS);
  }

  public static Uuid id() {
    return Uuid.generate();
  }

  public static Title title() {
    return new Title(titles.get(faker.number().numberBetween(0, titles.size())));
  }

  public static Subtitle subtitle() {
    return new Subtitle(subtitles.get(faker.number().numberBetween(0, titles.size())));
  }

  public static AdditionalInformation additionalInformation() {
    return new AdditionalInformation("For tickets call (555) 222-2222.");
  }

  public static TicketsQuantity ticketQuantity() {
    return new TicketsQuantity(Random.ticketsQuantity());
  }

  public static Email email() {
    return new Email(Random.email());
  }
}
