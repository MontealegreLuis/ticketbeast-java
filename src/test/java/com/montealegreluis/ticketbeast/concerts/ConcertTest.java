package com.montealegreluis.ticketbeast.concerts;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.Test;

final class ConcertTest {
  @Test
  public void it_knows_it_has_been_published() {
    var publishedConcert = aConcert().published().build();

    assertTrue(publishedConcert.isPublished());
  }

  @Test
  public void it_knows_if_it_occurs_after_a_given_date() {
    var now = Instant.parse("2022-02-07T00:00:00.00Z");
    var twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
    var inTwoDays = now.plus(2, ChronoUnit.DAYS);
    var concert = aConcert().published().onDate(now).build();

    assertFalse(concert.occursAfter(Date.from(inTwoDays)));
    assertTrue(concert.occursAfter(Date.from(twoDaysAgo)));
  }

  @Test
  void it_knows_its_current_values() {
    var id = Value.id();
    var title = Value.title();
    var subtitle = Value.subtitle();
    var date = Value.dateInFutureDays(10);
    var ticketPrice = Money.of(1000, "USD");
    var venue = aVenue().build();
    var additionalInformation = Value.additionalInformation();

    var concert =
        Concert.published(id, title, subtitle, date, ticketPrice, venue, additionalInformation);

    assertEquals(id, concert.id());
    assertEquals(title, concert.getTitle());
    assertEquals(subtitle, concert.getSubtitle());
    assertEquals(date, concert.getDate());
    assertEquals(ticketPrice, concert.getTicketPrice());
    assertEquals(venue, concert.getVenue());
    assertEquals(additionalInformation, concert.getAdditionalInformation());
    assertNotNull(concert.getPublishedAt());
  }

  @Test
  void it_calculates_the_price_for_a_given_quantity_of_tickets() {
    var concert = aConcert().withTicketPrice(Money.of(3000, "USD")).build();
    var quantity = 5;

    var ticketsPrice = concert.priceForTickets(quantity);

    assertEquals(Money.of(15000, "USD"), ticketsPrice);
  }

  @Test
  void it_can_be_compared_to_another_concert() {
    var concertA =
        aConcert().withId(Uuid.withValue("73f2adbf-6fa7-44a4-bf16-fdbe8b4aca7d")).build();
    var concertB =
        aConcert().withId(Uuid.withValue("46425d62-f0ef-465c-930b-0124d98079aa")).build();

    assertNotEquals(concertA, null);
    assertEquals(concertA, concertA);
    assertNotEquals(concertB, concertA);
    assertNotEquals(concertB, new Object());
    assertEquals(concertA.hashCode(), concertB.hashCode());
    assertNotEquals(concertA.hashCode(), new Object().hashCode());
    assertNotEquals(concertA.hashCode(), 0);
  }
}
