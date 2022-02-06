package com.montealegreluis.ticketbeast.concerts.venue;

import com.montealegreluis.ticketbeast.concerts.address.Address;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Venue {
  private final VenueName name;
  private final Address address;

  public Venue(VenueName name, Address address) {
    this.name = name;
    this.address = address;
  }
}
