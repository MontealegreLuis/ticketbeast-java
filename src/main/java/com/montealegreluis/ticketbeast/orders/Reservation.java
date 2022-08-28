package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.concerts.Ticket;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.Set;

public final class Reservation {
  private static final Money ZERO_DOLLARS = Money.of(0, "USD");
  private final Set<Ticket> tickets;
  private final Money total;
  private final Email email;

  public Reservation(final Set<Ticket> tickets, final Email email) {
    this.email = email;
    tickets.forEach(Ticket::reserve);
    this.tickets = tickets;
    total = this.tickets.stream().map(Ticket::price).reduce(ZERO_DOLLARS, Money::add);
  }

  public Set<Ticket> tickets() {
    return tickets;
  }

  public Email email() {
    return email;
  }

  public Money total() {
    return total;
  }

  public Order complete(
      final Uuid orderId,
      final ConfirmationNumber confirmationNumber,
      final ProcessedCharge charge) {
    return Order.place(orderId, confirmationNumber, email, tickets, charge);
  }
}
