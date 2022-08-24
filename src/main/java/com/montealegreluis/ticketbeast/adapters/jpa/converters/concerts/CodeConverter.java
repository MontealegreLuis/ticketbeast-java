package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import com.montealegreluis.ticketbeast.concerts.Code;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class CodeConverter implements AttributeConverter<Code, String> {
  @Override
  public String convertToDatabaseColumn(final Code code) {
    return code != null ? code.value() : null;
  }

  @Override
  public Code convertToEntityAttribute(final String code) {
    return code != null ? new Code(code) : null;
  }
}
