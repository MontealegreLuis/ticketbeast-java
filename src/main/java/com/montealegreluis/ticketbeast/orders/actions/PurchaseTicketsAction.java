package com.montealegreluis.ticketbeast.orders.actions;

import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.domainevents.EventBus;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import java.time.Clock;
import java.util.Date;

public final class PurchaseTicketsAction implements CommandHandler<PurchaseTicketsInput> {
  private final Concerts concerts;
  private final PaymentGateway payments;
  private final EventBus eventBus;
  private final Clock clock;

  public PurchaseTicketsAction(
      Concerts concerts, PaymentGateway payments, EventBus eventBus, Clock clock) {
    this.concerts = concerts;
    this.payments = payments;
    this.eventBus = eventBus;
    this.clock = clock;
  }

  @Override
  public void execute(PurchaseTicketsInput input) throws UnknownConcert, NotEnoughTickets {
    final PublishedConcertCriteria criteria =
        new PublishedConcertCriteria(input.concertId(), Date.from(clock.instant()));
    final Concert concert = concerts.matching(criteria);

    final Order order = concert.orderTickets(input.orderId(), input.quantity(), input.email());
    payments.charge(order.total(), input.token());

    concerts.save(concert);
    eventBus.dispatch(concert.events());
  }
}
