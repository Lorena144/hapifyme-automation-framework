Feature: Login functionality

  Scenario: Successful login
    Given user is on login page
    When user enters email "demo.user@hapifyme.test"
    And user enters password "DemoPass123!"
    And user clicks the login button
    Then user should be redirected to home page

  Scenario Outline: Invalid login
    Given user is on login page
    When user enters email "<email>"
    And user enters password "<password>"
    And user clicks the login button
    Then error message should be displayed "<error_message>"

    Examples:
      | email                    | password      | error_message                   |
      | admin@test.com           | WrongPass!123 | Email or password was incorrect |
      | user.inexistent@test.com | Pass!123      | Email or password was incorrect |
      | demo.user@hapifyme.test  | WrongPass!123 | Email or password was incorrect |
