package com.montealegreluis.ticketbeast.concerts.address;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Address {
  private final StreetAndNumber streetAndNumber;
  private final City city;
  private final State state;
  private final ZipCode zipCode;

  public Address(StreetAndNumber streetAndNumber, City city, State state, ZipCode zipCode) {
    this.streetAndNumber = streetAndNumber;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }
}
