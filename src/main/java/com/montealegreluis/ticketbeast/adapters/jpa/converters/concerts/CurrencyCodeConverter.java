package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import com.montealegreluis.ticketbeast.concerts.CurrencyCode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class CurrencyCodeConverter implements AttributeConverter<CurrencyCode, String> {
  @Override
  public String convertToDatabaseColumn(CurrencyCode currencyCode) {
    return currencyCode != null ? currencyCode.value() : null;
  }

  @Override
  public CurrencyCode convertToEntityAttribute(String currencyCode) {
    return currencyCode != null ? new CurrencyCode(currencyCode) : null;
  }
}
