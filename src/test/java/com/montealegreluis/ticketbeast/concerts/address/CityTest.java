package com.montealegreluis.ticketbeast.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.assertions.IllegalArgumentException;
import org.junit.jupiter.api.Test;

final class CityTest {
  @Test
  void it_cannot_be_blank() {
    assertThrows(IllegalArgumentException.class, () -> new City(" "));
    assertThrows(IllegalArgumentException.class, () -> new City(null));
  }

  @Test
  void it_knows_its_current_value() {
    var expectedCity = "Austin";

    var city = new City(expectedCity);

    assertEquals(expectedCity, city.value());
  }
}
