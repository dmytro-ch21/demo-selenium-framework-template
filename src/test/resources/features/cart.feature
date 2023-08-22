@removeFromCart
Feature:  Swag Labs - Cart Page

  Background:
    Given I am on the Products Page

  @regression
  Scenario Outline: Verify products can be removed from the cart
    When I add product with title "<title>" to the cart
    And I navigate to cart
    And I click remove product from the cart with title "<title>"
    Then the cart should no longer contain product "<title>"
    Examples:
      | title               |
      | Sauce Labs Backpack |
      | Sauce Labs Onesie   |
