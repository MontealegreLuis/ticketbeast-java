package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.concerts.address.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CityConverterTest {
  @Test
  void it_converts_city_to_database_type() {
    var cityName = "Austin";
    var city = new City(cityName);

    assertEquals(cityName, converter.convertToDatabaseColumn(city));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_city() {
    var cityName = "Austin";
    var city = new City(cityName);

    assertEquals(city, converter.convertToEntityAttribute(cityName));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new CityConverter();
  }

  private CityConverter converter;
}
