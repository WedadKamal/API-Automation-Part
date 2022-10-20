@smoke


Feature: F01_AddUserAndGetByID | user could add user and get user by ID

  Scenario: F01_AddUserAndGetByID | user could add user and get user by ID

    When fire add user request and print the ID
    And get user by ID
    Then assert on status code 201 for add user
    And assert on first name on "get user by ID" response
