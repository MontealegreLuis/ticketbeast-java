package com.montealegreluis.ticketbeast.orders.actions;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static com.montealegreluis.tickebeast.fakes.payments.FakePaymentGateway.VALID_TOKEN;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.montealegreluis.servicebuses.ActionException;
import com.montealegreluis.servicebuses.fakes.domainevents.FakeEventBus;
import com.montealegreluis.tickebeast.builders.Random;
import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.fakes.payments.FakePaymentGateway;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.OrderHasBeenPlaced;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
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
    var publishedUpcomingConcert = aConcert().published().onDate(inSevenDays).build();
    var criteria =
        new PublishedConcertCriteria(publishedUpcomingConcert.id(), Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenReturn(publishedUpcomingConcert);
    var concertId = publishedUpcomingConcert.id().value();
    var invalidPaymentToken = Random.uuid();
    var input = new PurchaseTicketsInput(concertId, 5, Random.email(), invalidPaymentToken);

    assertThrows(PaymentFailed.class, () -> action.execute(input));
  }

  @Test
  void it_completes_a_tickets_purchase() throws ActionException {
    var inSevenDays = clock.instant().plus(7, DAYS);
    var tenDollars = Money.of(1000, "USD");
    var publishedUpcomingConcert =
        aConcert().published().onDate(inSevenDays).withTicketPrice(tenDollars).build();
    var criteria =
        new PublishedConcertCriteria(publishedUpcomingConcert.id(), Date.from(clock.instant()));
    when(concerts.matching(criteria)).thenReturn(publishedUpcomingConcert);
    var ticketsQuantity = 5;
    var concertId = publishedUpcomingConcert.id().value();
    var input =
        new PurchaseTicketsInput(concertId, ticketsQuantity, Random.email(), VALID_TOKEN.value());

    action.execute(input);

    assertEquals(Money.of(5000, "USD"), payments.totalCharges());
    verify(concerts, times(1)).save(publishedUpcomingConcert);
    assertEquals(1, eventBus.dispatchedEventsCount());
    var event =
        new OrderHasBeenPlaced(
            input.orderId(), input.email(), publishedUpcomingConcert.id(), ticketsQuantity);
    assertTrue(eventBus.containsEvent(event));
  }

  @BeforeEach
  void let() {
    var now = Instant.parse("2022-03-19T10:37:30.00Z");
    payments = new FakePaymentGateway();
    concerts = mock(Concerts.class);
    eventBus = new FakeEventBus();
    clock = Clock.fixed(now, ZoneId.of("UTC"));
    action = new PurchaseTicketsAction(concerts, payments, eventBus, clock);
  }

  private PurchaseTicketsAction action;
  private FakePaymentGateway payments;
  private FakeEventBus eventBus;
  private Concerts concerts;
  private Clock clock;
}
