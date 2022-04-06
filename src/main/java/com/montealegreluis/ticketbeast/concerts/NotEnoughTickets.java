package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.ticketbeast.shared.InvalidAction;

public class NotEnoughTickets extends InvalidAction {
  public static NotEnoughTickets available(int availableTickets, int ticketsCount) {
    return new NotEnoughTickets(
        String.format(
            "Cannot place order, trying to purchase %d tickets, but only %d are available",
            ticketsCount, availableTickets));
  }

  private NotEnoughTickets(String message) {
    super(message);
  }
}
