Feature: CHANGE_PRODUCT_STATUS
  AS ADMIN I WOULD LIKE TO CHANGE PRODUCT STATUS TO AVAILABLE OR NOT_AVAILABLE

  Background:
    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    When user login to site with credential
    Then the status 'IS SUCCESSFUL'
    And returned token is not null

    Given admin wants to create new product using data
      | name          | price |
      | added_product | 1000  |

    When admin creates new product
    Then the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data

  Scenario: ADMIN SET STATUS OF AVAILABLE PRODUCT TO NOT AVAILABLE
    Given admin get product data
    And admin check if product is "AVAILABLE"
    When admin change product status to "NOT_AVAILABLE"
    Then admin get product data
    And admin check if product is "NOT_AVAILABLE"

  Scenario: ADMIN SET STATUS OF NOT AVAILABLE PRODUCT TO AVAILABLE
    Given admin get product data
    And admin check if product is "NOT_AVAILABLE"
    When admin change product status to "AVAILABLE"
    Then admin get product data
    And admin check if product is "AVAILABLE"

  Scenario: USER TRY TO SET STATUS OF PRODUCT TO AVAILABLE
    Given user wants to login using credentials
      | username | password |
      | user     | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    When admin change product status to "AVAILABLE"
    Then the status "HANDLE_FAILS"