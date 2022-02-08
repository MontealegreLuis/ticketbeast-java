package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.Test;

final class ConcertTest {
  @Test
  public void it_knows_it_has_been_published() {
    var publishedConcert = aConcert().published().build();

    assertTrue(publishedConcert.isPublished());
  }

  @Test
  public void it_knows_if_it_occurs_before_a_given_date() {
    var publishedConcert = aConcert().published().build();
    var twoDaysAfter = Date.from(publishedConcert.date().toInstant().plus(2, ChronoUnit.DAYS));
    var twoDaysBefore = Date.from(publishedConcert.date().toInstant().minus(2, ChronoUnit.DAYS));

    assertFalse(publishedConcert.occursBefore(twoDaysBefore));
    assertTrue(publishedConcert.occursBefore(twoDaysAfter));
  }

  @Test
  public void it_knows_it_occurs_before_a_given_date() {
    var now = Instant.parse("2022-02-07T00:00:00.00Z");
    var twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
    var inTwoDays = now.plus(2, ChronoUnit.DAYS);
    var concert = aConcert().published().onDate(now).build();

    assertTrue(concert.occursBefore(Date.from(inTwoDays)));
    assertFalse(concert.occursBefore(Date.from(twoDaysAgo)));
  }
}
