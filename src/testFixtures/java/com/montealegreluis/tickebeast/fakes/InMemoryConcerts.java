package com.montealegreluis.tickebeast.fakes;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import java.util.ArrayList;
import java.util.List;

public final class InMemoryConcerts implements Concerts {
  private final List<Concert> concerts = new ArrayList<>();

  @Override
  public Concert matching(PublishedConcertCriteria criteria) throws UnknownConcert {
    return concerts.stream()
        .filter(
            (concert) ->
                concert.id().equals(criteria.concertId())
                    && concert.isPublished()
                    && !concert.occursBefore(criteria.date()))
        .findFirst()
        .orElseThrow(() -> UnknownConcert.withId(criteria.concertId()));
  }

  @Override
  public void add(Concert concert) {
    concerts.add(concert);
  }
}
