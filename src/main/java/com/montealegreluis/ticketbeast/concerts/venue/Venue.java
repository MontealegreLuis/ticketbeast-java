package com.montealegreluis.ticketbeast.concerts.venue;

import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.venue.VenueNameConverter;
import com.montealegreluis.ticketbeast.concerts.address.Address;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Venue {
  @Convert(converter = VenueNameConverter.class)
  private VenueName name;

  @Embedded private Address address;

  public Venue(VenueName name, Address address) {
    this.name = name;
    this.address = address;
  }
}
