package com.montealegreluis.tickebeast.builders.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.*;
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
  private Money ticketPrice = Money.of(3250, "USD");
  private final Venue venue = aVenue().build();
  private final AdditionalInformation additionalInformation =
      new AdditionalInformation("For tickets call (555) 222-2222.");
  private TicketsQuantity ticketQuantity = Value.ticketQuantity();

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

  public ConcertBuilder withTicketPrice(Money price) {
    this.ticketPrice = price;
    return this;
  }

  public ConcertBuilder withTicketsCount(int ticketsCount) {
    ticketQuantity = new TicketsQuantity(ticketsCount);
    return this;
  }

  public Concert build() {
    final Concert concert =
        Concert.draft(
            concertId,
            title,
            subtitle,
            concertDate,
            ticketPrice,
            venue,
            additionalInformation,
            ticketQuantity);

    if (published) concert.publish();

    return concert;
  }
}
