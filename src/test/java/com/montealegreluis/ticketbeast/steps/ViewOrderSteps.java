package com.montealegreluis.ticketbeast.steps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.montealegreluis.tickebeast.fakes.concerts.InMemoryConcerts;
import com.montealegreluis.tickebeast.fakes.orders.InMemoryOrders;
import com.montealegreluis.tickebeast.fixtures.OrdersFixture;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.NotEnoughTickets;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.Orders;
import com.montealegreluis.ticketbeast.orders.UnknownOrder;
import com.montealegreluis.ticketbeast.orders.vieworder.ViewOrderAction;
import com.montealegreluis.ticketbeast.orders.vieworder.ViewOrderInput;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public final class ViewOrderSteps {
  @Given("an existing order with confirmation number {string}")
  public void an_existing_order_with_confirmation_number(String confirmationNumber)
      throws NotEnoughTickets {
    order = scenario.withAnOrderWithConfirmationNumber(confirmationNumber);
    orders.save(order);
  }

  @When("^I try to view the order details$")
  @When("I try to view an order details")
  public void i_try_to_view_the_order_details() {
    var confirmationNumber =
        order != null ? order.confirmationNumber().value() : "4FDF8F46249249FADF13DDC8";
    var input = new ViewOrderInput(confirmationNumber);

    try {
      existingOrder = action.execute(input);
    } catch (UnknownOrder ignore) {
    }
  }

  @Then("all the payment, concert and tickets information are available to me")
  public void all_the_payment_concert_and_tickets_information_are_available_to_me() {
    assertNotNull(existingOrder);
  }

  @Given("I haven't completed no order")
  public void i_haven_t_completed_no_order() {
    // Nothing to do here...
  }

  @Then("I see no order information")
  public void i_see_no_order_information() {
    assertNull(existingOrder);
  }

  private Order order;
  private Order existingOrder;
  private final Concerts concerts = new InMemoryConcerts();
  private final Orders orders = new InMemoryOrders();
  private final ViewOrderAction action = new ViewOrderAction(orders);
  private final OrdersFixture scenario = new OrdersFixture(concerts);
}
