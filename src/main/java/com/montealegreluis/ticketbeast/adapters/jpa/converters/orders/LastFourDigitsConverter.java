package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class LastFourDigitsConverter implements AttributeConverter<LastFourDigits, String> {
  @Override
  public String convertToDatabaseColumn(LastFourDigits lastFourDigits) {
    return lastFourDigits != null ? lastFourDigits.value() : null;
  }

  @Override
  public LastFourDigits convertToEntityAttribute(String lastFourDigits) {
    return lastFourDigits != null ? new LastFourDigits(lastFourDigits) : null;
  }
}
