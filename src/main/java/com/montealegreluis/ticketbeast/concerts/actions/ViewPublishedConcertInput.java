package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.ticketbeast.shared.Uuid;

public final class ViewPublishedConcertInput implements Query {
  private final Uuid concertId;

  public ViewPublishedConcertInput(String concertId) {
    this.concertId = Uuid.withValue(concertId);
  }

  public Uuid concertId() {
    return concertId;
  }
}
