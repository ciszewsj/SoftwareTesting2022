Feature: CREATE_PRODUCT
  AS ADMIN I WOULD LIKE TO CREATE A NEW PRODUCTS

  Background:
    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

  Scenario: ADMIN CREATE PRODUCT

    Given admin wants to create new product using data
      | name    | price |
      | product | 1000  |

    When admin creates new product
    Then the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data

  Scenario: ADMIN CREATE PRODUCT WITH UNAVAILABLE STATUS

    Given admin wants to create new product using data
      | name    | price | available |
      | product | 1000  | false     |

    When admin creates new product
    Then the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data

  Scenario: ADMIN CREATE PRODUCT WITH WRONG NAME

    Given admin wants to create new product using data
      | name | price |
      |      | 1000  |

    When admin creates new product
    Then the status 'HANDLE_FAILS'

  Scenario: ADMIN CREATE PRODUCT WITH PRICE LOWER THAN 0

    Given admin wants to create new product using data
      | name | price |
      | 123  | -1000 |

    When admin creates new product
    Then the status 'HANDLE_FAILS'

  Scenario: ADMIN CREATE PRODUCT WITH MAX NAME SIZE

    Given admin wants to create new product using data
      | name                                                                                                 | price |
      | ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss | 1000  |

    When admin creates new product
    Then the status 'IS SUCCESSFUL'
    And admin get product data
    And admin check added product data

  Scenario: ADMIN CREATE PRODUCT WITH TOO LONG NAME SIZE

    Given admin wants to create new product using data
      | name                                                                                                  | price |
      | sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss | 1000  |

    When admin creates new product
    Then the status 'HANDLE_FAILS'


  Scenario: NORMAL USER TRY TO CREATE PROFILE
    Given user wants to login using credentials
      | username | password |
      | user     | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null
    And admin wants to create new product using data
      | name    | price | available |
      | product | 1000  | false     |

    When admin creates new product
    Then the status 'UNAUTHORIZED'