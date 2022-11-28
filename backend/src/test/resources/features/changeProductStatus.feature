Feature: CHANGE_PRODUCT_STATUS
  AS ADMIN I WOULD LIKE TO CHANGE PRODUCT STATUS TO AVAILABLE OR NOT_AVAILABLE

  Background:
    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And admin wants to create new product using data
      | name          | price |
      | added_product | 1000  |

    And admin creates new product
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data

  Scenario: ADMIN SET STATUS OF AVAILABLE PRODUCT TO NOT AVAILABLE
    Given admin get product data
    And admin check if product is "AVAILABLE"
    When admin change product status to "NOT_AVAILABLE"
    And the status "IS SUCCESSFUL"
    Then admin get product data
    And admin check if product is "NOT_AVAILABLE"

  Scenario: ADMIN SET STATUS OF NOT AVAILABLE PRODUCT TO AVAILABLE
    Given admin change product status to "NOT_AVAILABLE"
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And the status 'IS SUCCESSFUL'
    And admin check if product is "NOT_AVAILABLE"
    When admin change product status to "AVAILABLE"
    And the status 'IS SUCCESSFUL'
    Then admin get product data
    And admin check if product is "AVAILABLE"

  Scenario: USER TRY TO SET STATUS OF PRODUCT TO AVAILABLE
    Given user wants to login using credentials
      | username | password |
      | user     | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null
    When admin change product status to "NOT_AVAILABLE"
    Then the status "HANDLE_FAILS"
    And admin get product data
    Then the status "HANDLE_FAILS"