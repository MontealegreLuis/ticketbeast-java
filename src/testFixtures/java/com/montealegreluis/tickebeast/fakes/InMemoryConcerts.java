package com.montealegreluis.tickebeast.fakes;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.ArrayList;
import java.util.List;

public final class InMemoryConcerts implements Concerts {
  private final List<Concert> concerts = new ArrayList<>();

  @Override
  public Concert publishedWithId(Uuid id) throws UnknownConcert {
    return concerts.stream()
        .filter((concert) -> id.equals(concert.id()) && concert.isPublished() && !concert.isPast())
        .findFirst()
        .orElseThrow(() -> UnknownConcert.withId(id));
  }

  @Override
  public void add(Concert concert) {
    concerts.add(concert);
  }
}
