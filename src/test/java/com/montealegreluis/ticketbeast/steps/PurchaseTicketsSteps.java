package com.montealegreluis.ticketbeast.steps;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.montealegreluis.servicebuses.fakes.domainevents.FakeEventBus;
import com.montealegreluis.tickebeast.builders.Random;
import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.tickebeast.fakes.orders.InMemoryOrders;
import com.montealegreluis.tickebeast.fakes.payments.FakePaymentGateway;
import com.montealegreluis.tickebeast.fakes.payments.InMemoryCharges;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.actions.PurchaseTicketsAction;
import com.montealegreluis.ticketbeast.orders.actions.PurchaseTicketsInput;
import com.montealegreluis.ticketbeast.payments.PaymentFailed;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public final class PurchaseTicketsSteps {
  @Given("a concert with {int} tickets available")
  public void aConcertWithTicketsAvailable(int ticketsCount) {
    paymentToken = charges.paymentToken(charges.creditCardNumber()).value();
    concert = aConcert().withTicketsCount(ticketsCount).build();
    concert.publish();
    concerts.save(concert);
    concert.events();
  }

  @When("I try to purchase {int} tickets")
  public void iTryToPurchaseTickets(int ticketsQuantity) {
    this.ticketsQuantity = ticketsQuantity;
    var input =
        new PurchaseTicketsInput(
            concert.id().value(), this.ticketsQuantity, Random.email(), paymentToken);

    try {
      action.execute(input);
    } catch (UnknownConcert | NotEnoughTickets | PaymentFailed ignore) {
    }
  }

  @Then("my payment is completed")
  public void myPaymentIsCompleted() {
    assertEquals(concert.priceForTickets(ticketsQuantity).getAmount(), charges.lastChargeAmount());
  }

  @And("my order is created successfully")
  public void myOrderIsCreatedSuccessfully() {
    assertEquals(1, eventBus.dispatchedEventsCount());
  }

  @Given("a past concert")
  public void aPastConcert() {
    paymentToken = charges.paymentToken(charges.creditCardNumber()).value();
    var fiveDaysAgo = now.minus(5, ChronoUnit.DAYS);
    concert = aConcert().tobeHeldOnDate(Date.from(fiveDaysAgo)).build();
    concert.publish();
    concerts.save(concert);
    concert.events();
  }

  @Then("no payment should be made")
  public void noPaymentShouldNotBeMade() {
    assertEquals(0, charges.lastChargeAmount());
  }

  @And("no order should be placed")
  public void noOrderShouldBePlaced() {
    assertEquals(0, eventBus.dispatchedEventsCount());
  }

  @And("an invalid payment token")
  public void anInvalidPaymentToken() {
    paymentToken = "268d5753-6ba7-4e08-97e9-1f35716fae5b";
  }

  private int ticketsQuantity;
  private String paymentToken;
  private Concert concert;
  private final Concerts concerts = new InMemoryConcerts();
  private final InMemoryCharges charges = new InMemoryCharges();
  private final FakePaymentGateway payments = new FakePaymentGateway(charges);
  private final FakeEventBus eventBus = new FakeEventBus();
  private final Instant now = Instant.parse("2022-02-07T00:00:00.00Z");
  private final PurchaseTicketsAction action =
      new PurchaseTicketsAction(
          new InMemoryOrders(), concerts, payments, eventBus, Clock.fixed(now, ZoneId.of("UTC")));
}
