Feature: Test Feature File

    # TODO: Move this file out of this project and into another area, then specify the location at run-time
    # The framework should only use the runner, even the steps may be moved to the other repo/project same as the features
    Scenario: The user should be able to login to the application
        Given the user opens the application
        And they navigate to the login page
        When the user inputs their valid credentials
        Then the user is brought to the home page

#        Given the user opens the browser
#        And closes the browser