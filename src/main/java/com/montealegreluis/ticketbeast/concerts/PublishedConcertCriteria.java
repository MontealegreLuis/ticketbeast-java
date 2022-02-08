package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Date;

public final class PublishedConcertCriteria {
  private final Uuid concertId;
  private final Date date;

  public PublishedConcertCriteria(Uuid concertId, Date date) {
    this.concertId = concertId;
    this.date = date;
  }

  public Uuid concertId() {
    return concertId;
  }

  public Date date() {
    return date;
  }
}
