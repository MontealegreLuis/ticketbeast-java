package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class SubtitleTest {
  @Test
  void it_cannot_be_blank_or_null() {
    assertThrows(IllegalArgumentException.class, () -> new Subtitle(" "));
    assertThrows(IllegalArgumentException.class, () -> new Subtitle(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedSubtitle = "with Animosity and the Lethargy";

    var subtitle = new Subtitle(expectedSubtitle);

    assertEquals(expectedSubtitle, subtitle.value());
  }
}
