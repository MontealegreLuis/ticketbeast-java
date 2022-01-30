package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;

public final class ViewPublishedConcert {
  private final Concerts concerts;

  public ViewPublishedConcert(Concerts concerts) {
    this.concerts = concerts;
  }

  public Concert view(int id) throws UnknownConcert {
    return concerts.publishedWithId(id);
  }
}
