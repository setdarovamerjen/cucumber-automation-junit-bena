@login
Feature: Login to application

  @login1
  Scenario: User with rights credentials must be able to login
    Given user is on login page
    When user enters email "sakydin.tashmurzaev.kk@gmail.com"
    And user enters password "CodeWise2023!"
    And  user clicks on login button
    Then verify that user must be logged in

  @login2
  Scenario: encrypted password alternative
    Given user is on login page
    When user enters correct email
    And user enters correct password
    And  user clicks on login button
    Then verify that user must be logged in