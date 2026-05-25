Feature: Create post

  Background:
    Given user is logged in

  @smoke
  Scenario: User creates a post successfully
    When user enters a post message "Test post "
    And user clicks the Post button
    Then the post should be displayed in the posts area

  @integration
  Scenario: User creates a post successfully and validates it through API

    Given user account is created via API
    And user logs in with generated credentials
    When user enters a post message "Integration test post"
    And user clicks the Post button
    Then the post should be displayed in the posts area
    And the created post should exist in API response