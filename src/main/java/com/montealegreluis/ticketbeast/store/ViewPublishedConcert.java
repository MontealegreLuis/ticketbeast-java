package com.montealegreluis.ticketbeast.store;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;

public class ViewPublishedConcert {
  private Concerts concerts;

  public ViewPublishedConcert(Concerts concerts) {
    this.concerts = concerts;
  }

  public Concert view(int id) throws UnknownConcert {
    return concerts.publishedWithId(id);
  }
}
