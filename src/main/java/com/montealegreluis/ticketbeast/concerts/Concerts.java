package com.montealegreluis.ticketbeast.concerts;

public interface Concerts {
  Concert matching(PublishedConcertCriteria criteria) throws UnknownConcert;

  void save(Concert concert);
}
