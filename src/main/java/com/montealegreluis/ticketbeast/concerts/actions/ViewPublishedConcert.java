package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.values.Uuid;

public final class ViewPublishedConcert {
  private final Concerts concerts;

  public ViewPublishedConcert(Concerts concerts) {
    this.concerts = concerts;
  }

  public Concert view(Uuid id) throws UnknownConcert {
    return concerts.publishedWithId(id);
  }
}
