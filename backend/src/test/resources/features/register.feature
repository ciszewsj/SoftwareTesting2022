Feature: REGISTER

  Scenario: USER CREATE ACCOUNT

    Given user wants to register using data
      | username | password |
      | new_user | password |

    When user register with credential
    Then the status 'IS SUCCESSFUL'

    And user wants to login using credentials
      | username | password |
      | new_user | password |
    And user login to site with credential
    And the status 'IS SUCCESSFUL'

  Scenario: USER CREATE ACCOUNT WITH NOT VALID DATA

    Given user wants to register using data
      | username | password |
      | us       | us       |

    When user register with credential
    Then the status 'HANDLE_FAILS'

  Scenario: USER CREATE ACCOUNT WITH NOT EMPTY DATA

    Given user wants to register using data
      | username | password |
      |          |          |

    When user register with credential
    Then the status 'HANDLE_FAILS'