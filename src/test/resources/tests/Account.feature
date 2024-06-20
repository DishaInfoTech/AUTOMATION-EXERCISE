@regression
Feature: Account statement

  Background:
    Given Account exists for Acc No. "12345678" with Name "Bob Smith"
    And deposits are made
      | INIT | 200 |
      | DEP1 | 100 |
      | DEP2 | 450 |
      | DEP3 | 50  |
    And withDrawls are made
      | CHQ001 | 675.55 |
    When statement is produced


  Scenario: Statement includes account details
    Then statement includes "Name: Bob Smith"
    * statement includes "Account: 12345678"


  Scenario: Balance is calculated on the statement
    Then statement includes "Balance: 124.45"


  Scenario: Statement includes transaction details
    Then statement includes "INIT"
    * statement includes "DEP1"
    * statement includes "DEP2"
    * statement includes "DEP3"
    * statement includes "CHQ001"
