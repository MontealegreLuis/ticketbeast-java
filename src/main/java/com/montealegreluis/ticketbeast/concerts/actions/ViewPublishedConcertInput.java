package com.montealegreluis.ticketbeast.concerts.actions;

import com.montealegreluis.ticketbeast.concerts.PublishedConcertCriteria;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Date;

public final class ViewPublishedConcertInput {
  private final Uuid concertId;

  public ViewPublishedConcertInput(String concertId) {
    this.concertId = Uuid.withValue(concertId);
  }

  public PublishedConcertCriteria criteria(Date now) {
    return new PublishedConcertCriteria(concertId, now);
  }
}
