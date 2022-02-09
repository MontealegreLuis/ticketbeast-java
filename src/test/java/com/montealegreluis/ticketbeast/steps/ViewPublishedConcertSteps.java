package com.montealegreluis.ticketbeast.steps;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.fakes.InMemoryConcerts;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.concerts.actions.ViewPublishedConcertAction;
import com.montealegreluis.ticketbeast.concerts.actions.ViewPublishedConcertInput;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public final class ViewPublishedConcertSteps {
  @Given("^a published concert$")
  public void a_published_concert() {
    var inTwoDays = now.plus(2, ChronoUnit.DAYS);
    existingConcert = aConcert().published().onDate(inTwoDays).build();
    concerts.add(existingConcert);
  }

  @When("^I try to view its details$")
  public void i_try_to_view_its_details() {
    try {
      concert = action.execute(new ViewPublishedConcertInput(existingConcert.id().value()));
    } catch (UnknownConcert ignore) {
    }
  }

  @Then("^all the concert details are available to me$")
  public void all_the_concert_details_are_available_to_me() {
    assertEquals(existingConcert, concert);
  }

  @Given("^an unpublished concert$")
  public void an_unpublished_concert() {
    existingConcert = aConcert().build();
    concerts.add(existingConcert);
  }

  @Then("^I see no information$")
  public void i_see_no_information() {
    assertNull(concert);
  }

  @Given("^a published concert with a date in the past$")
  public void a_published_concert_with_a_date_in_the_past() {
    var twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
    existingConcert = aConcert().published().onDate(twoDaysAgo).build();
    concerts.add(existingConcert);
  }

  private Concert concert;
  private Concert existingConcert;
  private final Concerts concerts = new InMemoryConcerts();
  private final Instant now = Instant.parse("2022-02-07T00:00:00.00Z");
  private final ViewPublishedConcertAction action =
      new ViewPublishedConcertAction(concerts, Clock.fixed(now, ZoneId.of("UTC")));
}
