Feature: PAY_FOR_CART
  AS USER I WOULD LIKE TO PAY FOR SHOPPING CART

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

  Scenario: user wants to pay for cart but cart is empty
    Given user wants to register using data
      | username | password |
      | user333  | password |
    And user register with credential
    And the status 'IS SUCCESSFUL'
    And user wants to login using credentials
      | username | password |
      | user333  | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    When user pay for cart
    Then the status "HANDLE_FAILS"

  Scenario: user wants to pay when is ok
    Given user wants to login using credentials
      | username | password |
      | user333  | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And user want to add "1" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    When user pay for cart
    Then the status "IS SUCCESSFUL"

  Scenario: user wants to pay for cart when product in cart is not available
    Given user wants to login using credentials
      | username | password |
      | user333  | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And user want to add "1" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user wants to login using credentials
      | username | password |
      | admin    | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And admin change product status to "NOT_AVAILABLE"
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And the status 'IS SUCCESSFUL'

    And user wants to login using credentials
      | username | password |
      | user333  | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    When user pay for cart
    Then the status "HANDLE_FAILS"
