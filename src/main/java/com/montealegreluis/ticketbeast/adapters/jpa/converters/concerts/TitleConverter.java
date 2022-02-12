package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import com.montealegreluis.ticketbeast.concerts.Title;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class TitleConverter implements AttributeConverter<Title, String> {
  @Override
  public String convertToDatabaseColumn(Title title) {
    return title != null ? title.value() : null;
  }

  @Override
  public Title convertToEntityAttribute(String title) {
    return title != null ? new Title(title) : null;
  }
}
