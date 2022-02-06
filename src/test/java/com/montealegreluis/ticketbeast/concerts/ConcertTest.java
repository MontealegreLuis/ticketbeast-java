package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aPastConcert;
import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aPublishedConcert;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class ConcertTest {
  @Test
  public void it_knows_it_has_been_published() {
    var publishedConcert = aPublishedConcert();

    assertTrue(publishedConcert.isPublished());
  }

  @Test
  public void it_knows_it_is_in_the_past() {
    var pastConcert = aPastConcert();

    assertTrue(pastConcert.isPast());
  }
}
