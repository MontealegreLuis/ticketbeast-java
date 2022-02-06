package com.montealegreluis.tickebeast.contracttests;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.junit.jupiter.api.Test;

public abstract class ConcertsContractTest {
  @Test
  public void it_does_not_find_an_unknown_concert() {
    assertThrows(UnknownConcert.class, () -> concerts().publishedWithId(null));
  }

  @Test
  public void it_does_not_find_an_unpublished_concert() {
    var concerts = concerts();
    var unpublishedConcert = anUnpublishedConcert();
    concerts.add(unpublishedConcert);

    assertThrows(UnknownConcert.class, () -> concerts.publishedWithId(unpublishedConcert.id()));
  }

  @Test
  public void it_does_not_find_a_concert_from_the_past() {
    var concerts = concerts();
    var pastConcert = aPastConcert();
    concerts.add(pastConcert);

    assertThrows(UnknownConcert.class, () -> concerts.publishedWithId(pastConcert.id()));
  }

  @Test
  public void it_finds_an_existing_concert() throws UnknownConcert {
    var concerts = concerts();
    var concert = aPublishedConcert();
    concerts.add(concert);

    assertEquals(concert, concerts.publishedWithId(concert.id()));
  }

  protected abstract Concerts concerts();
}
