package com.montealegreluis.ticketbeast.concerts.venue;

import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.venue.VenueNameConverter;
import com.montealegreluis.ticketbeast.concerts.address.Address;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Venue {
  @Column(name = "venue_name")
  @Convert(converter = VenueNameConverter.class)
  private VenueName name;

  @AttributeOverrides({
    @AttributeOverride(
        name = "streetAndNumber",
        column = @Column(name = "address_street_and_number")),
    @AttributeOverride(name = "city", column = @Column(name = "address_city")),
    @AttributeOverride(name = "state", column = @Column(name = "address_state")),
    @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code")),
  })
  @Embedded
  private Address address;

  public Venue(VenueName name, Address address) {
    this.name = name;
    this.address = address;
  }
}
