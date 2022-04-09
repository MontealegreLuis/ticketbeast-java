package com.montealegreluis.ticketbeast.concerts;

import java.util.Set;

public class Reservation {
  private static final Money ZERO_DOLLARS = Money.of(0, "USD");
  private final Set<Ticket> tickets;
  private final Money price;

  public Reservation(final Set<Ticket> tickets) {
    this.tickets = tickets;
    price = tickets.stream().map(Ticket::price).reduce(ZERO_DOLLARS, Money::add);
  }

  public Set<Ticket> tickets() {
    return tickets;
  }

  public Money cost() {
    return price;
  }
}
