package com.montealegreluis.ticketbeast.concerts.venue;

import static com.montealegreluis.tickebeast.builders.concerts.venue.VenueBuilder.aVenue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class VenueTest {
  @Test
  void it_can_be_compared_to_another_venue() {
    var venueA = aVenue().withName("Lake Austin").build();
    var venueB = aVenue().withName("The Mosh Pit").build();

    assertEquals(venueA, venueA);
    assertEquals(venueB, venueB);
    assertNotEquals(venueA, venueB);
    assertNotEquals(venueA, null);
    assertNotEquals(venueB, null);
  }
}
