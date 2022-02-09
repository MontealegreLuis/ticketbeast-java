package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import java.time.Clock;
import java.util.Date;

public final class ViewPublishedConcertAction {
  private final Concerts concerts;
  private final Clock clock;

  public ViewPublishedConcertAction(Concerts concerts, Clock clock) {
    this.concerts = concerts;
    this.clock = clock;
  }

  public Concert view(ViewPublishedConcertInput input) throws UnknownConcert {
    final var publishedConcertCriteria =
        new PublishedConcertCriteria(input.concertId(), Date.from(clock.instant()));

    return concerts.matching(publishedConcertCriteria);
  }
}
