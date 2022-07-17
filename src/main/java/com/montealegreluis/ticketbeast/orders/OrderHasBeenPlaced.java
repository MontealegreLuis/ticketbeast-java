package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import com.montealegreluis.servicebuses.domainevents.Identifier;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class OrderHasBeenPlaced extends DomainEvent {
  private final String orderId;
  private final String confirmationNumber;
  private final String email;
  private final List<String> ticketIds;

  public OrderHasBeenPlaced(
      Uuid orderId,
      ConfirmationNumber confirmationNumber,
      Email email,
      List<Identifier> ticketIds) {
    this.orderId = orderId.value();
    this.confirmationNumber = confirmationNumber.value();
    this.email = email.value();
    this.ticketIds = ticketIds.stream().map(Object::toString).collect(Collectors.toList());
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
