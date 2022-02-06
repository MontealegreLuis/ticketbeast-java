package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Instant;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Concert {
  private Uuid id;
  private String title;
  private String subtitle;
  private Date date;
  private int ticketPrice;
  private Venue venue;
  private String additionalInformation;
  private Date publishedAt;

  public static Concert published(
      Uuid id,
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    return new Concert(
        id,
        title,
        subtitle,
        date,
        ticketPrice,
        venue,
        additionalInformation,
        Date.from(Instant.now()));
  }

  public static Concert unpublished(
      Uuid id,
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    return new Concert(id, title, subtitle, date, ticketPrice, venue, additionalInformation);
  }

  private Concert(
      Uuid id,
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation,
      Date publishedAt) {
    this(id, title, subtitle, date, ticketPrice, venue, additionalInformation);
    this.publishedAt = publishedAt;
  }

  private Concert(
      Uuid id,
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    this.id = id;
    this.title = title;
    this.subtitle = subtitle;
    this.date = date;
    this.ticketPrice = ticketPrice;
    this.venue = venue;
    this.additionalInformation = additionalInformation;
  }

  public boolean isPublished() {
    return publishedAt != null;
  }

  public boolean isPast() {
    return date.before(new Date());
  }

  public Uuid id() {
    return id;
  }
}
