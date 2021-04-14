Feature: Login Functionality

  Scenario Outline:  Login with different credentials
    Given Launch the URL  "http://bookingportal.withamura.com"
    When Click 'Login with password instead'
    And Enter "<Username>" and "<Password>"
    Then Click on 'Sign in' button

    Examples:
    | Username | Password |
    | super-admin | amura123 |
    | sanket-superadmin@amuratech.com | amura123 |