@getNotes
Feature: Verify GET API notes endpoints

  Scenario: Verify valid POST creation of note
    Given user hits "/notes" post API creation of note
    Then HTTP status code must be 200

    Scenario: Verify valid GET creation
      Given user hits "/notes" get list API notes
      Then HTTP status code must be 200