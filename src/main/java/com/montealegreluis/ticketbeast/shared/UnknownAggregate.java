package com.montealegreluis.ticketbeast.shared;

import com.montealegreluis.servicebuses.DomainException;

public abstract class UnknownAggregate extends DomainException {
  protected UnknownAggregate(String message) {
    super(message);
  }
}
