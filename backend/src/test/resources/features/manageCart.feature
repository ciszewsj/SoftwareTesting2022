Feature: MANAGE_CART
  AS USER I WOULD LIKE TO ADD AND REMOVE PRODUCTS TO SHOPPING CART

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

  Scenario: user wants to put product to shopping cart when product is available
    Given user want to add "4" products to cart
    When user add product to cart
    Then the status 'IS SUCCESSFUL'
    And user load cart info
    And the status 'IS SUCCESSFUL'
    And user check if product added correctly and number is "4"

  Scenario: user wants to put product to shopping cart when product is in cart and is available
    Given user want to add "4" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And the status 'IS SUCCESSFUL'
    And user check if product added correctly and number is "4"
    And user want to add "8" products to cart
    When user add product to cart
    Then the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product added correctly and number is "12"

  Scenario: user wants to put product to shopping cart when product is not available
    Given user wants to login using credentials
      | username | password |
      | admin    | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null
    And admin get product data
    And the status 'IS SUCCESSFUL'
    And admin check if product is "AVAILABLE"
    And admin change product status to "NOT_AVAILABLE"
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check if product is "NOT_AVAILABLE"
    And user wants to login using credentials
      | username | password |
      | user     | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null
    And user want to add "4" products to cart
    When user add product to cart
    Then the status 'HANDLE_FAILS'

  Scenario: user wants to delete products from shopping cart when product is available
    Given user want to add "4" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And the status 'IS SUCCESSFUL'
    And user check if product added correctly and number is "4"
    And user want to add "8" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product added correctly and number is "12"
    And user want to remove "4" products from cart
    When user remove product from cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product removed correctly and number is "8"
    And user want to remove "10" products from cart
    And user remove product from cart
    Then the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product removed correctly and number is "0"

  Scenario: user wants to delete products from shopping cart when product are not_available
    Given user want to add "4" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And the status 'IS SUCCESSFUL'
    And user check if product added correctly and number is "4"
    And user want to add "8" products to cart
    And user add product to cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product added correctly and number is "12"

    And user wants to login using credentials
      | username | password |
      | admin    | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null
    And admin get product data
    And the status 'IS SUCCESSFUL'
    And admin check if product is "AVAILABLE"
    And admin change product status to "NOT_AVAILABLE"
    And the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check if product is "NOT_AVAILABLE"

    And user wants to login using credentials
      | username | password |
      | user     | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

    And user want to remove "4" products from cart
    When user remove product from cart
    And the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product removed correctly and number is "8"
    And user want to remove "10" products from cart
    And user remove product from cart
    Then the status 'IS SUCCESSFUL'
    And user load cart info
    And user check if product removed correctly and number is "0"

