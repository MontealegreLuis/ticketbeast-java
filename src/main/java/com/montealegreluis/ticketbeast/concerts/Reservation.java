package com.montealegreluis.ticketbeast.concerts;

import java.util.Set;

public final class Reservation {
  private static final Money ZERO_DOLLARS = Money.of(0, "USD");
  private final Set<Ticket> tickets;
  private final Money price;

  public Reservation(final Set<Ticket> tickets) {
    tickets.forEach(Ticket::reserve);
    this.tickets = tickets;
    price = this.tickets.stream().map(Ticket::price).reduce(ZERO_DOLLARS, Money::add);
  }

  public Set<Ticket> tickets() {
    return tickets;
  }

  public Money cost() {
    return price;
  }
}
