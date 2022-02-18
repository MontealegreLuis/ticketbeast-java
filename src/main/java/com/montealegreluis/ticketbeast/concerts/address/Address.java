package com.montealegreluis.ticketbeast.concerts.address;

import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address.CityConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address.StateConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address.StreetAndNumberConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.address.ZipCodeConverter;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Address {
  @Convert(converter = StreetAndNumberConverter.class)
  private StreetAndNumber streetAndNumber;

  @Convert(converter = CityConverter.class)
  private City city;

  @Convert(converter = StateConverter.class)
  private State state;

  @Convert(converter = ZipCodeConverter.class)
  private ZipCode zipCode;

  public Address(StreetAndNumber streetAndNumber, City city, State state, ZipCode zipCode) {
    this.streetAndNumber = streetAndNumber;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }
}
