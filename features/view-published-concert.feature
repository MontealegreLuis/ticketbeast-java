Feature: View upcoming concerts

  Scenario: View an upcoming published concert
    Given a published concert
    When I try to view its details
    Then all the concert details are available to me

  Scenario: Cannot view a concert that has not been published yet
    Given an unpublished concert
    When I try to view its details
    Then I see no information

  Scenario: Cannot view a past concert
    Given a published concert with a date in the past
    When I try to view its details
    Then I see no information
