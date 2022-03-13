package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.DomainException;
import com.montealegreluis.ticketbeast.values.Uuid;

public final class UnknownConcert extends DomainException {
  public static UnknownConcert withId(Uuid id) {
    return new UnknownConcert(String.format("Concert with ID %s cannot be found", id));
  }

  private UnknownConcert(String message) {
    super(message);
  }
}
