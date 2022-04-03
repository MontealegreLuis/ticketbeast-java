package com.montealegreluis.tickebeast.builders.orders;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.shared.Uuid;

public final class OrderBuilder {
  private TicketsQuantity ticketsQuantity = Value.ticketQuantity();
  private Uuid id = Value.id();

  public static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  public OrderBuilder withId(String id) {
    this.id = Uuid.withValue(id);
    return this;
  }

  public OrderBuilder withTicketsQuantity(int ticketsQuantity) {
    this.ticketsQuantity = new TicketsQuantity(ticketsQuantity);
    return this;
  }

  public Order build() {
    return Order.place(id, aConcert().build(), Value.email());
  }
}
