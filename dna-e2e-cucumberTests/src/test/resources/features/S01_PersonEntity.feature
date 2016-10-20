Feature: Individual person fields
#Check the stack trace for the value of scenario_val which is concatenated to the firstName to make it unique name each time the test run
  Scenario: Validate the person entity fields
    Given I create the person entity and updated the person record with the following details
      | firstName | Tory              |
      | lastName  | Cameron           |
      | Title     | Mr                |
      | dob       | 1955-10-26        |
      | address   | 10 Lambeth Street, Hemel Hempstead, Hertfordshire, UK, HP1 2DT; 20 Nelson Street, Lambeth, Lambeth Council, UK, SW1 2UF |
    When I get the person detials from postgres db
    Then person details name, dob and address are as below
      | firstName | Tory              |
      | lastName  | Cameron           |
      | Title     | Mr                |
      | dob       | 1955-10-26        |
      | address   | 10 Lambeth Street, Hemel Hempstead, Hertfordshire, UK, HP1 2DT; 20 Nelson Street, Lambeth, Lambeth Council, UK, SW1 2UF |