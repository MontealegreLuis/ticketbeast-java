package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import com.montealegreluis.ticketbeast.concerts.address.StreetAndNumber;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class StreetAndNumberConverter implements AttributeConverter<StreetAndNumber, String> {
  @Override
  public String convertToDatabaseColumn(StreetAndNumber streetAndNumber) {
    return streetAndNumber != null ? streetAndNumber.value() : null;
  }

  @Override
  public StreetAndNumber convertToEntityAttribute(String streetAndNumber) {
    return streetAndNumber != null ? new StreetAndNumber(streetAndNumber) : null;
  }
}
