Feature: ADD_COMMENT
  AS USER I WOULD LIKE TO ADD COMMENTS TO PRODUCTS

  Background:
    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And admin wants to create new product using data
      | name    | price |
      | product | 1000  |

    And admin creates new product
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data


    And user wants to login using credentials
      | username | password |
      | user     | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

  Scenario: USER ADD COMMENT WITH CORRECT DATA
    Given user want to add comment with data "comment"
    When user add comment with data
    Then the status "IS SUCCESSFUL"
    And user load product
    And the status "IS SUCCESSFUL"
    And user check if comment is added

  Scenario: USER ADD COMMENT WITH MAX LENGTH
    Given user want to add comment with data "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
    When user add comment with data
    Then the status "IS SUCCESSFUL"
    And user load product
    And the status "IS SUCCESSFUL"
    And user check if comment is added


  Scenario: USER ADD COMMENT WITH TOO LONG DATA
    Given user want to add comment with data "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
    When user add comment with data
    Then the status "HANDLE_FAILS"

  Scenario: USER ADD COMMENT WITH EMPTY COMMENT
    Given user want to add comment with data ""
    When user add comment with data
    Then the status "HANDLE_FAILS"


  Scenario: USER ADD COMMENT WHEN PRODUCT IS NOT AVAILABLE
    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And admin wants to create new product using data
      | name    | price | available |
      | product | 1000  | false     |

    And admin creates new product
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data


    And user wants to login using credentials
      | username | password |
      | user     | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    Given user want to add comment with data "comment"
    When user add comment with data
    Then the status "HANDLE_FAILS"