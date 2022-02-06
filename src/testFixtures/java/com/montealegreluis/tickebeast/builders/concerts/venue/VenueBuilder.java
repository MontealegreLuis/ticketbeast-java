package com.montealegreluis.tickebeast.builders.concerts.venue;

import static com.montealegreluis.tickebeast.builders.concerts.address.AddressBuilder.anAddress;

import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.concerts.venue.VenueName;

public final class VenueBuilder {
  private String venueName = "The Mosh Pit";

  public static VenueBuilder aVenue() {
    return new VenueBuilder();
  }

  public VenueBuilder withName(String venueName) {
    this.venueName = venueName;
    return this;
  }

  public Venue build() {
    return new Venue(new VenueName(venueName), anAddress().build());
  }
}
