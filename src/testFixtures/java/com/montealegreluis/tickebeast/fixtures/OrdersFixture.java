package com.montealegreluis.tickebeast.fixtures;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.adapters.hashids.HashIdsCodesGenerator;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;

public class OrdersFixture {
  private final Concerts concerts;

  public OrdersFixture(Concerts concerts) {
    this.concerts = concerts;
  }

  public Order withAnOrderWithTickets(int ticketsCount) throws NotEnoughTickets {
    var quantity = new TicketsQuantity(ticketsCount);
    var concert = aConcert().withTicketsCount(quantity.value()).build();
    concerts.save(concert);
    var orderId = Value.id();
    var reservation = concert.reserveTickets(quantity, Value.email());
    var confirmationNumber = Value.confirmationNumber();
    var charge = new ProcessedCharge(reservation.total(), new LastFourDigits("4242"));

    return reservation.complete(
        orderId, confirmationNumber, charge, new HashIdsCodesGenerator("a salt"));
  }
}
