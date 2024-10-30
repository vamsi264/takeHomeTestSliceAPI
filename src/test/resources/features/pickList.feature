Feature: Picklist
  In order to improve search experience
  As a User
  I want to be able to filter my search results

  @users-picklist
  Scenario Outline: Should be able to filter users results by activity type
    Given the user filters the "users" search results by "<Activity>"
    Then the users results should contain "id"
    And the user results should also contain "username"
    Examples:
      | Activity |
      | Calls    |
      | Messages |