package com.montealegreluis.tickebeast.contracttests;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.Test;

public abstract class ConcertsContractTest {
  @Test
  public void it_does_not_find_an_unknown_concert() {
    var criteria = new PublishedConcertCriteria(Value.id(), Value.dateInFutureDays(10));

    assertThrows(UnknownConcert.class, () -> concerts().matching(criteria));
  }

  @Test
  public void it_does_not_find_an_unpublished_concert() {
    var concerts = concerts();
    var unpublishedConcert = aConcert().build();
    concerts.save(unpublishedConcert);
    var criteria =
        new PublishedConcertCriteria(unpublishedConcert.id(), Value.dateInFutureDays(10));

    assertThrows(UnknownConcert.class, () -> concerts.matching(criteria));
  }

  @Test
  public void it_does_not_find_a_concert_from_the_past() {
    var concerts = concerts();
    var pastConcert = aConcert().build();
    concerts.save(pastConcert);
    var criteria = new PublishedConcertCriteria(pastConcert.id(), pastConcert.date());

    assertThrows(UnknownConcert.class, () -> concerts.matching(criteria));
  }

  @Test
  public void it_finds_an_existing_concert() throws UnknownConcert {
    var concerts = concerts();
    var concert = aConcert().published().build();
    concerts.save(concert);
    var twoDaysBefore = Date.from(concert.date().toInstant().minus(2, ChronoUnit.DAYS));
    var criteria = new PublishedConcertCriteria(concert.id(), twoDaysBefore);

    assertEquals(concert, concerts.matching(criteria));
  }

  protected abstract Concerts concerts();
}
