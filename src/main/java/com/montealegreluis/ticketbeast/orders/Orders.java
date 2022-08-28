package com.montealegreluis.ticketbeast.orders;

public interface Orders {
  void save(Order order);

  Order with(ConfirmationNumber confirmationNumber) throws UnknownOrder;
}
