# Step 3
Feature: Print the account statement to the console

  Scenario:
    Given "John Doe" makes a deposit of 1000 on 10-01-2012
    And a deposit of 2000 on 13-01-2012
    And a withdrawal of 500 on 14-01-2012
    When he prints his bank statement
    Then he would see
      | date       | credit  | debit    | balance |
      | 14/01/2012 |         | 500.00   | 2500.00 |
      | 13/01/2012 | 2000.00 |          | 3000.00 |
      | 10/01/2012 | 1000.00 |          | 1000.00 |
