package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.ticketbeast.values.Uuid;

public final class ViewPublishedConcertInput {
  private final Uuid concertId;

  public ViewPublishedConcertInput(String concertId) {
    this.concertId = Uuid.withValue(concertId);
  }

  public Uuid concertId() {
    return concertId;
  }
}
