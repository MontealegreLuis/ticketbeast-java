package com.montealegreluis.tickebeast.builders.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.concerts.Subtitle;
import com.montealegreluis.ticketbeast.concerts.Title;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Instant;
import java.util.Date;

public final class ConcertBuilder {
  private boolean published = false;
  private Uuid concertId = Uuid.generate();
  private final Title title = new Title("The red chord");
  private final Subtitle subtitle = new Subtitle("with Animosity and the Lethargy");
  private Date concertDate = Value.dateInFutureDays(10);
  private final Money ticketPrice = Money.of(3250, "USD");
  private final Venue venue = aVenue().build();
  private final String additionalInformation = "For tickets call (555) 222-2222.";

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
