package com.montealegreluis.ticketbeast.orders.actions;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeEventBus;
import com.montealegreluis.tickebeast.builders.Random;
import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.contracttests.Charges;
import com.montealegreluis.tickebeast.fakes.payments.FakePaymentGateway;
import com.montealegreluis.tickebeast.fakes.payments.InMemoryCharges;
import com.montealegreluis.ticketbeast.adapters.hashids.HashIdsCodesGenerator;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import com.montealegreluis.ticketbeast.payments.PaymentToken;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class PurchaseTicketsActionTest {
  @Test
  void it_fails_to_complete_a_purchase_if_concert_cannot_be_found() throws UnknownConcert {
    var concertId = Value.id();
    var criteria = new PublishedConcertCriteria(concertId, Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenThrow(UnknownConcert.withId(concertId));
    var input =
        new PurchaseTicketsInput(
            concertId.value(), Random.ticketsQuantity(), Random.email(), Random.uuid());

    assertThrows(UnknownConcert.class, () -> action.execute(input));
  }

  @Test
  void it_fails_to_complete_a_purchase_if_payment_fails() throws ActionException {
    var inSevenDays = clock.instant().plus(7, DAYS);
    var publishedUpcomingConcert =
        aConcert().published().withTicketsCount(10).onDate(inSevenDays).build();
    var criteria =
        new PublishedConcertCriteria(publishedUpcomingConcert.id(), Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenReturn(publishedUpcomingConcert);
    var concertId = publishedUpcomingConcert.id().value();
    var invalidPaymentToken = Random.uuid();
    var input = new PurchaseTicketsInput(concertId, 5, Random.email(), invalidPaymentToken);

    assertThrows(PaymentFailed.class, () -> action.execute(input));
  }

  @Test
  void it_fails_to_completes_a_purchase_if_there_are_not_enough_tickets_available()
      throws UnknownConcert {
    var inSevenDays = clock.instant().plus(7, DAYS);
    var publishedUpcomingConcert =
        aConcert().published().withTicketsCount(5).onDate(inSevenDays).build();
    var criteria =
        new PublishedConcertCriteria(publishedUpcomingConcert.id(), Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenReturn(publishedUpcomingConcert);
    var concertId = publishedUpcomingConcert.id().value();
    var moreTicketsThanAvailable = 10;
    var input =
        new PurchaseTicketsInput(
            concertId, moreTicketsThanAvailable, Random.email(), token.value());

    assertThrows(NotEnoughTickets.class, () -> action.execute(input));
  }

  @Test
  void it_completes_a_tickets_purchase() throws ActionException {
    var inSevenDays = clock.instant().plus(7, DAYS);
    var tenDollars = Money.of(1000, "USD");
    var ticketsQuantity = 5;
    var publishedUpcomingConcert =
        aConcert()
            .published()
            .withTicketsCount(ticketsQuantity)
            .onDate(inSevenDays)
            .withTicketPrice(tenDollars)
            .build();
    publishedUpcomingConcert.events();
    var criteria =
        new PublishedConcertCriteria(publishedUpcomingConcert.id(), Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenReturn(publishedUpcomingConcert);
    var concertId = publishedUpcomingConcert.id().value();
    var input = new PurchaseTicketsInput(concertId, ticketsQuantity, Random.email(), token.value());

    action.execute(input);

    assertEquals(5000, charges.lastChargeAmount());
    verify(orders, times(1)).save(any(Order.class));
    assertEquals(1, eventBus.dispatchedEventsCount());
  }

  @BeforeEach
  void let() {
    var now = Instant.parse("2022-03-19T10:37:30.00Z");
    charges = new InMemoryCharges();
    token = charges.paymentToken(charges.creditCardNumber());
    var payments = new FakePaymentGateway((InMemoryCharges) charges);
    concerts = mock(Concerts.class);
    orders = mock(Orders.class);
    eventBus = new FakeEventBus();
    clock = Clock.fixed(now, ZoneId.of("UTC"));
    action =
        new PurchaseTicketsAction(
            orders, concerts, payments, eventBus, new HashIdsCodesGenerator("A salt"), clock);
  }

  private Clock clock;
  private Orders orders;
  private Charges charges;
  private Concerts concerts;
  private FakeEventBus eventBus;
  private PurchaseTicketsAction action;
  private PaymentToken token;
}
