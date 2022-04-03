package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.shared.UnknownAggregate;
import com.montealegreluis.ticketbeast.shared.Uuid;

public final class UnknownConcert extends UnknownAggregate {
  public static UnknownConcert withId(Uuid id) {
    return new UnknownConcert(String.format("Concert with ID %s cannot be found", id));
  }

  private UnknownConcert(String message) {
    super(message);
  }
}
