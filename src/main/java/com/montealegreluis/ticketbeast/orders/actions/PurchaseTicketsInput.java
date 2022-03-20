package com.montealegreluis.ticketbeast.orders.actions;

import com.montealegreluis.servicebuses.commandbus.Command;
import com.montealegreluis.ticketbeast.orders.Email;
import com.montealegreluis.ticketbeast.orders.TicketsQuantity;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import com.montealegreluis.ticketbeast.values.Uuid;

public final class PurchaseTicketsInput implements Command {
  private final Uuid concertId;
  private final TicketsQuantity quantity;
  private final Email email;
  private final PaymentToken token;
  private final Uuid orderId;

  public PurchaseTicketsInput(
      final String concertId, final int quantity, String email, final String token) {
    this.concertId = Uuid.withValue(concertId);
    this.quantity = new TicketsQuantity(quantity);
    this.email = new Email(email);
    this.token = new PaymentToken(token);
    this.orderId = Uuid.generate();
  }

  public Uuid concertId() {
    return concertId;
  }

  public TicketsQuantity quantity() {
    return quantity;
  }

  public PaymentToken token() {
    return token;
  }

  public Email email() {
    return email;
  }

  public Uuid orderId() {
    return orderId;
  }
}
