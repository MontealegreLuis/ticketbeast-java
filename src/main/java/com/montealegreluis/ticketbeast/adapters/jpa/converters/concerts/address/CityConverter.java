package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import com.montealegreluis.ticketbeast.concerts.address.City;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class CityConverter implements AttributeConverter<City, String> {
  @Override
  public String convertToDatabaseColumn(City city) {
    return city != null ? city.value() : null;
  }

  @Override
  public City convertToEntityAttribute(String city) {
    return city != null ? new City(city) : null;
  }
}
