@Test-Borrowing-Calculator
Feature: Acceptance testing to validate borrowing calculator functionality

  Background: User navigates to ANZ Borrowing Calculator Page
    Given user navigates to ANZ Borrowing Calculator Page

 @Borrow-Cal
  Scenario: Validate borrowing calculation based upon input values
    Given user is on webPage with Title "Home loan borrowing power calculator | ANZ"
    When user enters the personal details
      | Application Type|   Dependants | Property Type|Income|Other Income|Living Expenses|Current Home Loan Repayments|Other Loan Repayments|Other Commitments|Total Credit Card Limits|
      | Single | 0                     | Home |80000 |10000      |500          |0                  |100                |0               |10000               |
    And user clicks on "Work out how much I could borrow" button
    Then user verifies the "Borrowing Limit" as "$479,000"

  @Start-Over
  Scenario: Validate start over clear all input values
    Given user is on webPage with Title "Home loan borrowing power calculator | ANZ"
    When user enters the personal details
      | Application Type|   Dependants | Property Type|Income|Other Income|Living Expenses|Current Home Loan Repayments|Other Loan Repayments|Other Commitments|Total Credit Card Limits|
      | Single | 0                     | Home |80000 |10000      |500          |0                  |100                |0               |10000               |
    And user clicks on "Work out how much I could borrow" button
    Then user verifies the "Borrowing Limit" as "$507,000"
    When user clicks on "Start Over" button
    Then user verifies the input field "Income" as "0"
    Then user verifies the input field "Other Income" as "0"
    Then user verifies the input field "Living Expenses" as "0"
    Then user verifies the input field "Current Home Loan Repayments" as "0"
    Then user verifies the input field "Other Loan Repayments" as "0"
    Then user verifies the input field "Other Commitments" as "0"
    Then user verifies the input field "Other Commitments" as "0"
    Then user verifies the input field "Total Credit Card Limits" as "0"

  @InsufficientInfo
  Scenario: Validate Message when Insufficient information provided to calculate borrowing power
    Given user is on webPage with Title "Home loan borrowing power calculator | ANZ"
    When user enters the personal details
      | Living Expenses |
      | 1          |
    And user clicks on "Work out how much I could borrow" button
    Then user verifies the "Message" as "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641."

    