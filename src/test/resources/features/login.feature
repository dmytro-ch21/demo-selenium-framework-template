@login
Feature: Swag Labs - Login Page

  Background:
    Given I land on Swag Labs page

  @regression
  Scenario: Validate Successful Login
    When I enter a username "standard_user"
    And I enter a password "secret_sauce"
    And I click on the login button
    Then I should be able to see the "Products" title

  @regression
  Scenario Outline: Validate Unsuccessful Login with <test>
    When I enter a username "<username>"
    And I enter a password "<password>"
    And I click on the login button
    Then I should see the error message "<errorMessage>"

    Examples:
      | test                          | username  | password      | errorMessage                                                              |
      | Invalid username and password | test_user | test_password | Epic sadface: Username and password do not match any user in this service |
      | Empty password                | test_user |               | Epic sadface: Password is required                                        |
      | Empty username                |           | test_password | Epic sadface: Username is required                                        |

