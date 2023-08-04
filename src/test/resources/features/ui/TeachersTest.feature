Feature: all tests related to Teachers page

  @verifyNumberOfTeachers
  Scenario: Verify the number of teachers per one page is displayed correctly.
    Given user is on login page
    When user enters correct email
    And user enters correct password
    And user clicks on login button
    Then user navigates to teachers tab
    Then verify user changes the number of results per page to
      | 5  |
      | 10 |
      | 3  |




