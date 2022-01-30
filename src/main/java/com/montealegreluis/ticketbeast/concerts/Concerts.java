package com.montealegreluis.ticketbeast.concerts;

public interface Concerts {
  Concert publishedWithId(int id) throws UnknownConcert;

  void add(Concert concert);
}
