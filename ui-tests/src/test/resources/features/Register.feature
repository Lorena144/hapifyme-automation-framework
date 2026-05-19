Feature: Register functionality

  Scenario: Successful register
    Given user is on register page
    When  user fills register form
      | firstName       | Test                    |
      | lastName        | Automat                 |
      | email           | demo.user@hapifyme.test |
      | confirmEmail    | demo.user@hapifyme.test |
      | password        | DemoPass123!            |
      | confirmPassword | DemoPass123!            |
    And user clicks the register button
    Then the successful registration message should be displayed