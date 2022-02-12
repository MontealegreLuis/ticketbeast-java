package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import com.montealegreluis.ticketbeast.concerts.Subtitle;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class SubtitleConverter implements AttributeConverter<Subtitle, String> {
  @Override
  public String convertToDatabaseColumn(Subtitle subtitle) {
    return subtitle != null ? subtitle.value() : null;
  }

  @Override
  public Subtitle convertToEntityAttribute(String subtitle) {
    return subtitle != null ? new Subtitle(subtitle) : null;
  }
}
