package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.venue;

import com.montealegreluis.ticketbeast.concerts.venue.VenueName;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class VenueNameConverter implements AttributeConverter<VenueName, String> {
  @Override
  public String convertToDatabaseColumn(VenueName venueName) {
    return venueName != null ? venueName.value() : null;
  }

  @Override
  public VenueName convertToEntityAttribute(String venueName) {
    return venueName != null ? new VenueName(venueName) : null;
  }
}
