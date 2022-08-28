package com.montealegreluis.tickebeast.fixtures;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.ticketbeast.adapters.hashids.HashIdsCodesGenerator;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.concerts.TicketsQuantity;
import com.montealegreluis.ticketbeast.orders.ConfirmationNumber;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;

public class OrdersFixture {
  private static final HashIdsCodesGenerator generator = new HashIdsCodesGenerator("a salt");
  private final Concerts concerts;

  public OrdersFixture(Concerts concerts) {
    this.concerts = concerts;
  }

  public Order withAnOrderWithTickets(int ticketsCount) throws NotEnoughTickets {
    var quantity = new TicketsQuantity(ticketsCount);
    var concert = withAConcertWithTickets(ticketsCount);
    var reservation = concert.reserveTickets(quantity, Value.email());

    return reservation.complete(
        Value.id(),
        Value.confirmationNumber(),
        new ProcessedCharge(reservation.total(), new LastFourDigits("4242")),
        generator);
  }

  public Order withAnOrderWithConfirmationNumber(String aConfirmationNumber)
      throws NotEnoughTickets {
    var quantity = new TicketsQuantity(2);
    var concert = withAConcertWithTickets(quantity.value());
    var reservation = concert.reserveTickets(quantity, Value.email());
    var confirmationNumber = new ConfirmationNumber(aConfirmationNumber);

    return reservation.complete(
        Value.id(),
        confirmationNumber,
        new ProcessedCharge(reservation.total(), new LastFourDigits("4242")),
        generator);
  }

  private Concert withAConcertWithTickets(int ticketsCount) {
    var quantity = new TicketsQuantity(ticketsCount);
    var concert = aConcert().withTicketsCount(quantity.value()).build();
    concerts.save(concert);
    return concert;
  }
}
