package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

final class ConcertTest {
  @Test
  public void it_knows_it_has_been_published() {
    Concert published =
        Concert.published(
            "The red chord",
            "with Animosity and the Lethargy",
            Date.valueOf(LocalDate.parse("2017-01-01")),
            3250,
            "The Mosh Pit",
            "123 Example lane",
            "Laraville",
            "ON",
            "17196",
            "For tickets call (555) 222-2222.");

    assertTrue(published.isPublished());
  }

  @Test
  public void it_knows_it_is_in_the_past() {
    Concert published =
        Concert.published(
            "The red chord",
            "with Animosity and the Lethargy",
            Date.valueOf(LocalDate.parse("2015-01-01")),
            3250,
            "The Mosh Pit",
            "123 Example lane",
            "Laraville",
            "ON",
            "17196",
            "For tickets call (555) 222-2222.");

    assertTrue(published.isPast());
  }
}
