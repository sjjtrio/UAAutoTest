@API @Example
Feature: API automation test examples

  Background:
    Given API test data for weather is ready

    Scenario: API test example
      Given I send request to weather service by coordinates
      Then  The response code should be 200
      And   The response body should contain the coordinates





