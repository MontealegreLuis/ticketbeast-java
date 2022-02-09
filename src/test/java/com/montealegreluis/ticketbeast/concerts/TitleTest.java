package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class TitleTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new Title(" "));
    assertThrows(IllegalArgumentException.class, () -> new Title(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedTitle = "The red chord";

    var title = new Title(expectedTitle);

    assertEquals(expectedTitle, title.value());
  }
}
