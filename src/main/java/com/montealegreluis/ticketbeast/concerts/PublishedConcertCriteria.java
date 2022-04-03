package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.Date;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class PublishedConcertCriteria {
  private final Uuid concertId;
  private final Date currentDate;

  public PublishedConcertCriteria(Uuid concertId, Date currentDate) {
    this.concertId = concertId;
    this.currentDate = currentDate;
  }

  public Uuid concertId() {
    return concertId;
  }

  public Date currentDate() {
    return currentDate;
  }
}
