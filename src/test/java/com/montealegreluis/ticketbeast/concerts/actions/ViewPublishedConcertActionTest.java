package com.montealegreluis.ticketbeast.concerts.actions;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.fakes.InMemoryConcerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ViewPublishedConcertActionTest {
  @Test
  void it_finds_an_upcoming_published_concert() throws UnknownConcert {
    var inTwoDays = now.plus(2, ChronoUnit.DAYS);
    var existingConcert =
        aConcert().withId(Uuid.withValue(concertId)).published().onDate(inTwoDays).build();
    concerts.add(existingConcert);
    var input = new ViewPublishedConcertInput(concertId);

    var publishedConcert = action.view(input);

    assertEquals(existingConcert, publishedConcert);
  }

  @Test
  void it_fails_to_find_an_unpublished_concert() {
    var existingConcert = aConcert().withId(Uuid.withValue(concertId)).build();
    var input = new ViewPublishedConcertInput(concertId);
    concerts.add(existingConcert);

    assertThrows(UnknownConcert.class, () -> action.view(input));
  }

  @Test
  void it_fails_to_find_a_published_concert_from_the_past() {
    var twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
    var existingConcert =
        aConcert().withId(Uuid.withValue(concertId)).published().onDate(twoDaysAgo).build();
    concerts.add(existingConcert);
    var input = new ViewPublishedConcertInput(concertId);

    assertThrows(UnknownConcert.class, () -> action.view(input));
  }

  @BeforeEach
  void let() {
    concerts = new InMemoryConcerts();
    concertId = "a0d3749f-2bff-463a-ba14-8f05e6f498be";
    now = Instant.parse("2022-02-07T00:00:00.00Z");
    action = new ViewPublishedConcertAction(concerts, Clock.fixed(now, ZoneId.of("UTC")));
  }

  private ViewPublishedConcertAction action;
  private InMemoryConcerts concerts;
  private String concertId;
  private Instant now;
}
