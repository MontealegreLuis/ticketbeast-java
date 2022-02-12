package com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address;

import com.montealegreluis.ticketbeast.concerts.address.State;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class StateConverter implements AttributeConverter<State, String> {
  @Override
  public String convertToDatabaseColumn(State state) {
    return state != null ? state.value() : null;
  }

  @Override
  public State convertToEntityAttribute(String state) {
    return state != null ? new State(state) : null;
  }
}
