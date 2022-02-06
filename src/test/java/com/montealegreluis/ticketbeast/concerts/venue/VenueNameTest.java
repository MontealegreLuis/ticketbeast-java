package com.montealegreluis.ticketbeast.concerts.venue;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class VenueNameTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new VenueName(" "));
    assertThrows(IllegalArgumentException.class, () -> new VenueName(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedVenueName = "The Mosh Pit";

    var venueName = new VenueName(expectedVenueName);

    assertEquals(expectedVenueName, venueName.value());
  }
}
