Feature: Login functionality

  @smoke
  Scenario: Successful login
    Given user is on login page
    When user logs in with valid credentials
    And user clicks the login button
    Then user should be redirected to home page

  @regression
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

  @integration
  Scenario: Login with API created user
    Given user account is created via API
    And user is on login page
    When user logs in with generated credentials
    Then user should be redirected to home page