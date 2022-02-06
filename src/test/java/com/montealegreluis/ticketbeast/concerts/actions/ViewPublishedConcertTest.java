package com.montealegreluis.ticketbeast.concerts.actions;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.fakes.InMemoryConcerts;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ViewPublishedConcertTest {
  @Test
  void it_finds_an_upcoming_published_concert() throws UnknownConcert {
    Concert existingConcert = aPublishedConcert();
    concerts.add(existingConcert);

    Concert publishedConcert = action.view(1);

    assertEquals(existingConcert, publishedConcert);
  }

  @Test
  void it_fails_to_find_an_unpublished_concert() {
    Concert existingConcert = anUnpublishedConcert();
    concerts.add(existingConcert);

    assertThrows(UnknownConcert.class, () -> action.view(1));
  }

  @Test
  void it_fails_to_find_a_published_concert_from_the_past() {
    Concert existingConcert = aPastConcert();
    concerts.add(existingConcert);

    assertThrows(UnknownConcert.class, () -> action.view(1));
  }

  @BeforeEach
  void let() {
    concerts = new InMemoryConcerts();
    action = new ViewPublishedConcert(concerts);
  }

  private ViewPublishedConcert action;
  private InMemoryConcerts concerts;
}
