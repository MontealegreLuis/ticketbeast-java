package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts;

import com.montealegreluis.ticketbeast.concerts.AdditionalInformation;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class AdditionalInformationConverter
    implements AttributeConverter<AdditionalInformation, String> {
  @Override
  public String convertToDatabaseColumn(AdditionalInformation additionalInformation) {
    return additionalInformation != null ? additionalInformation.value() : null;
  }

  @Override
  public AdditionalInformation convertToEntityAttribute(String additionalInformation) {
    return additionalInformation != null ? new AdditionalInformation(additionalInformation) : null;
  }
}
