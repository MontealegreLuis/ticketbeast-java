package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import com.montealegreluis.ticketbeast.values.Uuid;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class OrderHasBeenPlaced extends DomainEvent {
  private final String orderId;
  private final String email;
  private final String concertId;
  private final int ticketsCount;

  public OrderHasBeenPlaced(Uuid orderId, Email email, Uuid concertId, int ticketsCount) {
    this.orderId = orderId.value();
    this.email = email.value();
    this.concertId = concertId.value();
    this.ticketsCount = ticketsCount;
  }

  @Override
  public String name() {
    return "concerts.orderHasBeenPlaced";
  }

  @Override
  public String aggregateId() {
    return orderId;
  }
}
