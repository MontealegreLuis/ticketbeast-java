package com.montealegreluis.ticketbeast.concerts;

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
  private String venue;
  private String venueAddress;
  private String city;
  private String state;
  private String zip;
  private String additionalInformation;
  private Date publishedAt;

  private Concert(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      String venue,
      String venueAddress,
      String city,
      String state,
      String zip,
      String additionalInformation) {
    this.title = title;
    this.subtitle = subtitle;
    this.date = date;
    this.ticketPrice = ticketPrice;
    this.venue = venue;
    this.venueAddress = venueAddress;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.additionalInformation = additionalInformation;
  }

  private Concert(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      String venue,
      String venueAddress,
      String city,
      String state,
      String zip,
      String additionalInformation,
      Date publishedAt) {
    this(
        title,
        subtitle,
        date,
        ticketPrice,
        venue,
        venueAddress,
        city,
        state,
        zip,
        additionalInformation);
    this.publishedAt = publishedAt;
  }

  public static Concert published(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      String venue,
      String venueAddress,
      String city,
      String state,
      String zip,
      String additionalInformation) {
    return new Concert(
        title,
        subtitle,
        date,
        ticketPrice,
        venue,
        venueAddress,
        city,
        state,
        zip,
        additionalInformation,
        Date.from(Instant.now()));
  }

  public static Concert unpublished(
      String title,
      String subtitle,
      Date date,
      int ticketPrice,
      String venue,
      String venueAddress,
      String city,
      String state,
      String zip,
      String additionalInformation) {
    return new Concert(
        title,
        subtitle,
        date,
        ticketPrice,
        venue,
        venueAddress,
        city,
        state,
        zip,
        additionalInformation);
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
