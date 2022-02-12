package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.address.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class StateConverterTest {
  @Test
  void it_converts_state_to_database_value() {
    var stateName = "Texas";
    var state = new State(stateName);

    assertEquals(stateName, converter.convertToDatabaseColumn(state));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_state() {
    var stateName = "Texas";
    var state = new State(stateName);

    assertEquals(state, converter.convertToEntityAttribute(stateName));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new StateConverter();
  }

  private StateConverter converter;
}
