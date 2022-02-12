package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import com.montealegreluis.ticketbeast.concerts.address.ZipCode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class ZipCodeConverter implements AttributeConverter<ZipCode, String> {
  @Override
  public String convertToDatabaseColumn(ZipCode zipCode) {
    return zipCode != null ? zipCode.value() : null;
  }

  @Override
  public ZipCode convertToEntityAttribute(String zipCode) {
    return zipCode != null ? new ZipCode(zipCode) : null;
  }
}
