package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import java.time.Instant;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Concert {
  private int id;
  private String title;
  private String subtitle;
  private Date date;
  private int ticketPrice;
  private Venue venue;
  private String additionalInformation;
  private Date publishedAt;

  private Concert(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    this.title = title;
    this.subtitle = subtitle;
    this.date = date;
    this.ticketPrice = ticketPrice;
    this.venue = venue;
    this.additionalInformation = additionalInformation;
  }

  private Concert(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation,
      Date publishedAt) {
    this(title, subtitle, date, ticketPrice, venue, additionalInformation);
    this.publishedAt = publishedAt;
  }

  public static Concert published(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    return new Concert(
        title, subtitle, date, ticketPrice, venue, additionalInformation, Date.from(Instant.now()));
  }

  public static Concert unpublished(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      Venue venue,
      String additionalInformation) {
    return new Concert(title, subtitle, date, ticketPrice, venue, additionalInformation);
  }

  public boolean isPublished() {
    return publishedAt != null;
  }

  public boolean isPast() {
    return date.before(new Date());
  }

  public int id() {
    return id;
  }
}
