@products
Feature:  Swag Labs - Products Page

  Background:
    Given I am on the Products Page

  @regression
  Scenario: Verify products can be added to the cart
    Given the cart should be empty
    When I add product with title "Sauce Labs Backpack" to the cart
    Then I verify product is successfully added to the cart

  @regression
  Scenario: Verify products can be removed from the cart
    When I add product with title "Sauce Labs Backpack" to the cart
    And I remove a product from the cart with title "Sauce Labs Backpack"
    Then the product should be removed

  @regression
  Scenario Outline: Verify products can be sorted
    And I sort products by sortType "<sortType>"
    Then products should be sorted by sortType "<sortType>"
    Examples:
      | sortType            |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

  @regression
  Scenario Outline: Verify product detail access by clicking attribute
    When I click on the product "<attribute>"
    Then I should redirected to the product detail page
    Examples:
      | attribute |
      | Name      |
      | Image     |

  @regression
  Scenario: Validate Successful Logout
    And I click open menu button
    And I click on logout
    Then I should be able logout successfully