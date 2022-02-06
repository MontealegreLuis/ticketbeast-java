package com.montealegreluis.ticketbeast.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class StateTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new State(" "));
    assertThrows(IllegalArgumentException.class, () -> new State(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedState = "Texas";

    var state = new State(expectedState);

    assertEquals(expectedState, state.value());
  }
}
