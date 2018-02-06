package com.montealegreluis.ticketbeast.steps;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.builders.A;
import com.montealegreluis.ticketbeast.fakes.InMemoryConcerts;
import com.montealegreluis.ticketbeast.store.ViewPublishedConcert;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class ViewConcertSteps {

    @Given("^a published concert$")
    public void a_published_concert() {
        existingConcert = A.publishedConcert();
        concerts.add(existingConcert);
    }

    @When("^I try to view its details$")
    public void i_try_to_view_its_details() {
        concert = action.view(1);
    }

    @Then("^all the concert details are available to me$")
    public void all_the_concert_details_are_available_to_me() {
        assertEquals(existingConcert, concert);
    }

    @Given("^an unpublished concert$")
    public void an_unpublished_concert() throws Throwable {
        throw new PendingException();
    }

    @Then("^I see no information$")
    public void i_see_no_information() throws Throwable {
        throw new PendingException();
    }

    @Given("^a published concert with a date in the past$")
    public void a_published_concert_with_a_date_in_the_past() throws Throwable {
        throw new PendingException();
    }

    private Concert concert;
    private Concert existingConcert;
    private Concerts concerts = new InMemoryConcerts();
    private ViewPublishedConcert action = new ViewPublishedConcert(concerts);
}
