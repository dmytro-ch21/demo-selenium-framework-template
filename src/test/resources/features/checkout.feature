@checkout_fields
Feature:  Swag Labs - Your Information Page

  Background:
    Given I login to the Swag Labs website
    And I add product with title "Sauce Labs Backpack" to the cart
    And I click on cart
    And I click on checkout button

    @regression
    Scenario Outline: Validate fields requirement with <test>
      Given I'm on Checkout: Information page
      When I enter the first name "<firstName>"
      And I enter the last name "<lastName>"
      And I enter postal code "<postalCode>"
      And I click on continue button
      Then I should see the error message "<errorMessage>"
      Examples:
      | test            | firstName  | lastName | postalCode | errorMessage                   |
      | Empty first name |           | TestLast | 12345      | Error: First Name is required  |
      | Empty last name | TestFirst |          | 12345      | Error: Last Name is required   |
      | Empty zip code  | TestFirst | TestLast |            | Error: Postal Code is required |