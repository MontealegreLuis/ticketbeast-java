Feature: Purchase Tickets

  Scenario: Complete tickets purchase
    Given a concert with 5 tickets available
    When I try to purchase 2 tickets
    Then my payment is completed
    And my order is created successfully
  Scenario: Concert cannot be found
    Given a past concert
    When I try to purchase 2 tickets
    Then no payment should be made
    And no order should be placed
  Scenario: Not enough tickets available
    Given a concert with 1 tickets available
    When I try to purchase 2 tickets
    Then no payment should be made
    And no order should be placed
  Scenario: Payment failed
    Given a concert with 5 tickets available
    And an invalid payment token
    When I try to purchase 2 tickets
    Then no payment should be made
    And no order should be placed
