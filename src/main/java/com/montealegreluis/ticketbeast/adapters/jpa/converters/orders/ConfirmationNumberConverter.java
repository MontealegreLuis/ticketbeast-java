package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import com.montealegreluis.ticketbeast.orders.ConfirmationNumber;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class ConfirmationNumberConverter
    implements AttributeConverter<ConfirmationNumber, String> {
  @Override
  public String convertToDatabaseColumn(ConfirmationNumber confirmationNumber) {
    return confirmationNumber != null ? confirmationNumber.value() : null;
  }

  @Override
  public ConfirmationNumber convertToEntityAttribute(String confirmationNumber) {
    return confirmationNumber != null ? new ConfirmationNumber(confirmationNumber) : null;
  }
}
