package com.montealegreluis.tickebeast.builders.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;

import com.github.javafaker.Faker;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public final class ConcertBuilder {
  private static final Faker faker = new Faker();

  public static Concert aPublishedConcert() {
    return Concert.published(
        Uuid.generate(),
        "The red chord",
        "with Animosity and the Lethargy",
        faker.date().future(10, TimeUnit.DAYS),
        3250,
        aVenue().build(),
        "For tickets call (555) 222-2222.");
  }

  public static Concert anUnpublishedConcert() {
    return Concert.unpublished(
        Uuid.generate(),
        "The red chord",
        "with Animosity and the Lethargy",
        Date.valueOf(LocalDate.parse("2017-12-13")),
        3250,
        aVenue().build(),
        "For tickets call (555) 222-2222.");
  }

  public static Concert aPastConcert() {
    return Concert.published(
        Uuid.generate(),
        "The red chord",
        "with Animosity and the Lethargy",
        faker.date().past(10, TimeUnit.DAYS),
        3250,
        aVenue().build(),
        "For tickets call (555) 222-2222.");
  }
}
