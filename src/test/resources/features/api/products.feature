@api
Feature: Products and services

  Scenario: Verify every products or service and category fields
    Given user hits get all products api with "/api/myaccount/products" params page 1 and size 10
    Then user verifies all the products service types and categories.

    Scenario: Verify that every service type id's
      Given user hits get all products api with "/api/myaccount/products" params page 1 and size 10
      Then user verifies service types to be "Service" or "Product"




