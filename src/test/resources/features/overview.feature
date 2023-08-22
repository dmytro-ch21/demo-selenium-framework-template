@overview
Feature: Swag Labs - Overview Page

  Background:
    Given Given I login to the Swag Labs website
    And I add product with title "Sauce Labs Backpack" to the cart
    And I navigate to cart
    And I proceed to checkout
    And I enter the first name "TestFirstName"
    And I enter the last name "TestLastName"
    And I enter postal code "12345"
    And I click on continue button

  @regression
  Scenario: Verify item total amount
    Then the item total amount should be correct

  @regression
  Scenario: Verify order checkout is completed
    When I click on finish button on checkout page
    Then I should see "Thank you for your order!" message