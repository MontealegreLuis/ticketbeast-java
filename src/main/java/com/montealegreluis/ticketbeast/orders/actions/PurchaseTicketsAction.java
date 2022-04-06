package com.montealegreluis.ticketbeast.orders.actions;

import com.montealegreluis.servicebuses.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.domainevents.EventBus;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import java.time.Clock;
import java.util.Set;

@Command
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
  public void execute(PurchaseTicketsInput input)
      throws UnknownConcert, NotEnoughTickets, PaymentFailed {

    final Concert concert = concerts.matching(input.criteria(clock.instant()));

    final Set<Ticket> tickets = concert.availableTickets(input.quantity());

    final Money ticketsPrice = concert.priceForTickets(tickets.size());
    payments.charge(ticketsPrice, input.token());

    concert.placeOrder(input.orderId(), tickets, input.email(), ticketsPrice);

    concerts.save(concert);
    eventBus.dispatch(concert.events());
  }
}
