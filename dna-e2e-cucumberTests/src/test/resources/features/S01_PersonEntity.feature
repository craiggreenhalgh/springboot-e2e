
Feature: Individual person fields

  Scenario: Validate the person entity fields
    Given I create the person entity and updated the person record with the following details
      | firstName    | Tory               |
      |lastName      |Cameron             |
      |Title         |Mr                  |
      | dob     | 1955-10-26        |
      | address | 10 Downing Street |
    When I get the person detials from postgres db
    Then person details name, dob and address are as below
      | Tory              |
      | 1955-10-26        |
      | 10 Downing Street |