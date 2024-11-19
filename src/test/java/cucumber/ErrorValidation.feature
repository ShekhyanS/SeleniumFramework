Feature: Error Validation

#//Scenario Outline is used when you are using data with Examples
#  you can use tags like @ErrorValidation, text is free, like groups in testng
  @ErrorValidation
  Scenario Outline: Negative test for order submission
    Given I landed on Ecommerce page
    When Logged in with username <name> and <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                  | password    |
      | rahulshetty@gmail.com | Iamking@000 |
