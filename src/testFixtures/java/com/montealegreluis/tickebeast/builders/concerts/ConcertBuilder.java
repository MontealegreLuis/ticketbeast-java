package com.montealegreluis.tickebeast.builders.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Instant;
import java.util.Date;

public final class ConcertBuilder {
  private boolean published = false;
  private Uuid concertId = Uuid.generate();
  private String title = "The red chord";
  private String subtitle = "with Animosity and the Lethargy";
  private Date concertDate = Value.dateInFutureDays(10);
  private int ticketPrice = 3250;
  private Venue venue = aVenue().build();
  private String additionalInformation = "For tickets call (555) 222-2222.";

  public static ConcertBuilder aConcert() {
    return new ConcertBuilder();
  }

  public ConcertBuilder published() {
    published = true;
    return this;
  }

  public ConcertBuilder onDate(Instant instant) {
    this.concertDate = Date.from(instant);
    return this;
  }

  public ConcertBuilder withId(Uuid id) {
    concertId = id;
    return this;
  }

  public Concert build() {
    if (published) {
      return Concert.published(
          concertId, title, subtitle, concertDate, ticketPrice, venue, additionalInformation);
    }
    return Concert.unpublished(
        concertId, title, subtitle, concertDate, ticketPrice, venue, additionalInformation);
  }
}
