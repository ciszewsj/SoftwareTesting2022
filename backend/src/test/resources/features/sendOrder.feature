Feature: SEND_ORDER
  AS ADMIN I WOULD LIKE TO SEND PAID ORDERS TO USERS

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


    Given user wants to register using data
      | username | password |
      | sender   | password |
    And user register with credential
    And the status 'IS SUCCESSFUL'

    And user wants to login using credentials
      | username | password |
      | sender   | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And user want to add "1" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user pay for cart
    And the status "IS SUCCESSFUL"

    And user wants to login using credentials
      | username | password |
      | admin    | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

  Scenario: admin send order when data is ok
    Given admin load orders
    And the status 'IS SUCCESSFUL'

    And admin find order of player "sender"
    And admin valid status is "PAID"
    Then admin sent order
    And the status 'IS SUCCESSFUL'

    And admin load order data
    And the status 'IS SUCCESSFUL'

    And admin valid status is "SENT"
