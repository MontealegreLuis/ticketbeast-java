package com.montealegreluis.ticketbeast.concerts.viewpublishedconcert;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.shared.Uuid;
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
    concerts.save(existingConcert);
    var input = new ViewPublishedConcertInput(concertId);

    var publishedConcert = action.execute(input);

    assertEquals(existingConcert, publishedConcert);
  }

  @Test
  void it_fails_to_find_an_unpublished_concert() {
    var existingConcert = aConcert().withId(Uuid.withValue(concertId)).build();
    var input = new ViewPublishedConcertInput(concertId);
    concerts.save(existingConcert);

    assertThrows(UnknownConcert.class, () -> action.execute(input));
  }

  @Test
  void it_fails_to_find_a_published_concert_from_the_past() {
    var twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
    var existingConcert =
        aConcert().withId(Uuid.withValue(concertId)).published().onDate(twoDaysAgo).build();
    concerts.save(existingConcert);
    var input = new ViewPublishedConcertInput(concertId);

    assertThrows(UnknownConcert.class, () -> action.execute(input));
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
