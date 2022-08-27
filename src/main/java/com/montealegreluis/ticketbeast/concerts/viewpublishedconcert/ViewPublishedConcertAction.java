package com.montealegreluis.ticketbeast.concerts.viewpublishedconcert;

import com.montealegreluis.servicebuses.Query;
import com.montealegreluis.servicebuses.querybus.QueryHandler;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import java.time.Clock;
import java.util.Date;

@Query
public final class ViewPublishedConcertAction
    implements QueryHandler<ViewPublishedConcertInput, Concert> {
  private final Concerts concerts;
  private final Clock clock;

  public ViewPublishedConcertAction(Concerts concerts, Clock clock) {
    this.concerts = concerts;
    this.clock = clock;
  }

  public Concert execute(ViewPublishedConcertInput input) throws UnknownConcert {
    final var publishedConcertCriteria =
        new PublishedConcertCriteria(input.concertId(), Date.from(clock.instant()));

    return concerts.matching(publishedConcertCriteria);
  }
}
