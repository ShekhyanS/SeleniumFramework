Feature: Purchase order from Ecommerce Website
#//this is the section where you pub precondition
  Background:
    Given I landed on Ecommerce page
#//Scenario Outline is used when you are using data with Examples
  @Regression
  Scenario Outline: Positive test for order submission
    Given Logged in with username <name> and <password>
    When When I add product <productName> to Cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples:
    |name                   | password    | productName |
    |rahulshetty@gmail.com  | Iamking@000 | ZARA COAT 3 |
    |sona@email.com         | Sonatest@1  | ZARA COAT 3 |