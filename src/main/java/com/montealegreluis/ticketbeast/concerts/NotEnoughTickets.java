package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.orders.Email;
import com.montealegreluis.ticketbeast.shared.InvalidAction;

public class NotEnoughTickets extends InvalidAction {
  public static NotEnoughTickets availableFor(Email email, int availableTickets, int ticketsCount) {
    return new NotEnoughTickets(
        String.format(
            "Cannot place order for %s, trying to purchase %d tickets, but only %d are available",
            email.value(), ticketsCount, availableTickets));
  }

  private NotEnoughTickets(String message) {
    super(message);
  }
}
