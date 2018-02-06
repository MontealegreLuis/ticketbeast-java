package com.montealegreluis.ticketbeast.steps;

import com.montealegreluis.ticketbeast.builders.A;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.fakes.InMemoryConcerts;
import com.montealegreluis.ticketbeast.store.ViewPublishedConcert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ViewConcertSteps {

    @Given("^a published concert$")
    public void a_published_concert() {
        existingConcert = A.publishedConcert();
        concerts.add(existingConcert);
    }

    @When("^I try to view its details$")
    public void i_try_to_view_its_details() {
        try {
            concert = action.view(1);
        } catch (UnknownConcert ignore) {}
    }

    @Then("^all the concert details are available to me$")
    public void all_the_concert_details_are_available_to_me() {
        assertEquals(existingConcert, concert);
    }

    @Given("^an unpublished concert$")
    public void an_unpublished_concert() {
        existingConcert = A.unpublishedConcert();
        concerts.add(existingConcert);
    }

    @Then("^I see no information$")
    public void i_see_no_information() {
        assertNull(concert);
    }

    @Given("^a published concert with a date in the past$")
    public void a_published_concert_with_a_date_in_the_past() {
        existingConcert = A.pastConcert();
        concerts.add(existingConcert);
    }

    private Concert concert;
    private Concert existingConcert;
    private Concerts concerts = new InMemoryConcerts();
    private ViewPublishedConcert action = new ViewPublishedConcert(concerts);
}
