package com.montealegreluis.ticketbeast.adapters.jpa.repostories.concerts;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.springframework.stereotype.Repository;

@Repository
public class ConcertsRepository implements Concerts {
  private final JpaConcerts concerts;

  public ConcertsRepository(JpaConcerts concerts) {
    this.concerts = concerts;
  }

  @Override
  public Concert matching(PublishedConcertCriteria criteria) throws UnknownConcert {
    return concerts
        .findByIdAndDateAfterAndPublishedAtNotNull(criteria.concertId(), criteria.currentDate())
        .orElseThrow(() -> UnknownConcert.withId(criteria.concertId()));
  }

  @Override
  public void add(Concert concert) {
    concerts.save(concert);
  }
}
