package com.montealegreluis.ticketbeast.orders.actions;

import com.montealegreluis.servicebuses.Command;
import com.montealegreluis.servicebuses.commandbus.CommandHandler;
import com.montealegreluis.servicebuses.domainevents.EventBus;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.Reservation;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentGateway;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
import java.time.Clock;

@Command
public final class PurchaseTicketsAction implements CommandHandler<PurchaseTicketsInput> {
  private final Orders orders;
  private final Concerts concerts;
  private final PaymentGateway payments;
  private final EventBus eventBus;
  private final Clock clock;

  public PurchaseTicketsAction(
      final Orders orders,
      final Concerts concerts,
      final PaymentGateway payments,
      final EventBus eventBus,
      final Clock clock) {
    this.orders = orders;
    this.concerts = concerts;
    this.payments = payments;
    this.eventBus = eventBus;
    this.clock = clock;
  }

  @Override
  public void execute(final PurchaseTicketsInput input)
      throws UnknownConcert, NotEnoughTickets, PaymentFailed {

    final Concert concert = concerts.matching(input.criteria(clock.instant()));

    final Reservation reservation = concert.reserveTickets(input.quantity(), input.email());
    final ProcessedCharge charge = payments.charge(reservation.total(), input.token());
    final Order order = reservation.complete(input.orderId(), input.confirmationNumber(), charge);

    orders.save(order);
    eventBus.dispatch(order.events());
  }
}
