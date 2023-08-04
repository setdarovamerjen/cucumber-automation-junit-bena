Feature: all tests related to students

  Background:
    Given user is on login page
    When user enters email "sakydin.tashmurzaev.kk@gmail.com"
    And user enters password "CodeWise2023!"
    And  user clicks on login button
    Then user navigates to students tab

    @studentstest
  Scenario Outline: Verify admin can create and delete students
    Then user clicks on add student button
    Then user adds student info "<fist_name>", "<last_name>", "<phone_number>", "<email>", "<group>", "<study_format>"
    And user clicks on add add button
    Then verify student with "<fist_name>", "<last_name>", "<phone_number>", "<email>", "<group>", "<study_format>" was created
    Then user deletes student with "<email>"
    Then verify student with "<fist_name>", "<last_name>", "<phone_number>", "<email>", "<group>", "<study_format>" was deleted
Examples:
  | fist_name | last_name | phone_number | email                  | group              | study_format |
  | Sarah     | Parker    | 123456789    | sarah.parker@gmail.com | Batch1687036529983 | ONLINE       |
  | John      | Park      | 098765432    | john.park@gmail.com    | Batch1687036529983 | OFFLINE      |
  | Kim       | Lee       | 102938476    | kim.lee@gmail.com      | Batch1687036529983 | ONLINE       |
  | Lorie     | Beaber    | 123098476    | Lorie.Beaber@gmail.com | Batch1687036529983 | ONLINE       |



