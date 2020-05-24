@UI @Example
Feature: This is an example feature file for UI automation test
  Following scenarios are examples

  Background: 
    Given The pre-condition of test is ready

    Scenario: Search engine website test
      Given I open home page of bing
      When  I search Automation Testing
      Then  The results should be correct