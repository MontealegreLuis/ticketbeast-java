package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.values.Uuid;

public final class UnknownConcert extends Exception {
  public static UnknownConcert withId(Uuid id) {
    return new UnknownConcert(String.format("Unknown concert with id %s cannot be found", id));
  }

  private UnknownConcert(String message) {
    super(message);
  }
}
