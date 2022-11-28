Feature: LOGIN
  AS NOT LOGGED IN USER I WOULD LIKE TO LOGIN TO APPLICATION

  Scenario: USER LOGIN TO SITE

    Given user wants to login using credentials
      | username | password |
      | user     | password |

    And user login to site with credential
    And the status 'IS SUCCESSFUL'
    And returned token is not null

  Scenario: ADMIN LOGIN TO SITE

    Given user wants to login using credentials
      | username | password |
      | admin    | password |

    When user login to site with credential
    Then the status 'IS SUCCESSFUL'
    And returned token is not null

  Scenario: USER LOGIN TO SITE WITH WRONG USERNAME

    Given user wants to login using credentials
      | username  | password |
      | not_exist | user     |

    When user login to site with credential
    Then the status 'HANDLE_FAILS'

  Scenario: USER LOGIN TO SITE WITH WRONG PASSWORD

    Given user wants to login using credentials
      | username | password |
      | user     | user     |

    When user login to site with credential
    Then the status 'HANDLE_FAILS'