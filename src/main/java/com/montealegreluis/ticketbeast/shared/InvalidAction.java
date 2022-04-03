package com.montealegreluis.ticketbeast.shared;

import com.montealegreluis.servicebuses.DomainException;

public abstract class InvalidAction extends DomainException {
  public InvalidAction(String message) {
    super(message);
  }
}
