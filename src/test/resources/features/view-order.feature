Feature: View an existing order

  Scenario: View an existing order
    Given an existing order with confirmation number "7235BC826C433957633D813E"
    When I try to view the order details
    Then all the payment, concert and tickets information are available to me

  Scenario: Cannot view an unknown order
    Given I haven't completed no order
    When I try to view an order details
    Then I see no order information
