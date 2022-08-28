package com.montealegreluis.ticketbeast.orders.vieworder;

import com.montealegreluis.servicebuses.querybus.Query;
import com.montealegreluis.ticketbeast.orders.ConfirmationNumber;

public class ViewOrderInput implements Query {
  private final ConfirmationNumber confirmationNumber;

  public ViewOrderInput(String confirmationNumber) {
    this.confirmationNumber = new ConfirmationNumber(confirmationNumber);
  }

  public ConfirmationNumber confirmationNumber() {
    return confirmationNumber;
  }
}
