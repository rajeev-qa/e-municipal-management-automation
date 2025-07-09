Feature: Grievance Registration
  As a citizen
  I want to register a grievance
  So that my issues can be addressed

  Background:
    Given I am on the grievance portal
    And the grievance dialog is displayed

  @smoke @grievance
  Scenario: Register grievance with valid data
    When I click register grievance without login
    And I accept terms and conditions
    And I enter grievance details from "water_issue"
    And I fill user information from "valid_user"
    And I solve the captcha
    Then the grievance should be registered successfully
    And I should see the grievance number

  @regression @grievance
  Scenario Outline: Register grievance with different issue types
    When I click register grievance without login
    And I accept terms and conditions
    And I enter grievance details from "<issue_type>"
    And I fill user information from "valid_user"
    And I solve the captcha
    Then the grievance should be registered successfully

    Examples:
      | issue_type       |
      | water_issue      |
      | electricity_issue|

  @negative @grievance
  Scenario: Register grievance with invalid user data
    When I click register grievance without login
    And I accept terms and conditions
    And I enter grievance details from "water_issue"
    And I fill user information from "invalid_user"
    Then I should see validation errors