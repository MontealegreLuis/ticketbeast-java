package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.values.Uuid;

public interface Concerts {
  Concert publishedWithId(Uuid id) throws UnknownConcert;

  void add(Concert concert);
}
