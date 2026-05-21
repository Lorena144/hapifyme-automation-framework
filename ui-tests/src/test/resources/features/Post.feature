Feature: Create post

  Background:
    Given user is logged in

  @smoke
  Scenario: User creates a post successfully
    When user enters a post message "Test post "
    And user clicks the Post button
    Then the post should be displayed in the posts area