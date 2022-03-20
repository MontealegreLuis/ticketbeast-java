package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import com.montealegreluis.ticketbeast.orders.Email;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class EmailConverter implements AttributeConverter<Email, String> {
  @Override
  public String convertToDatabaseColumn(Email email) {
    return email != null ? email.value() : null;
  }

  @Override
  public Email convertToEntityAttribute(String email) {
    return email != null ? new Email(email) : null;
  }
}
