Feature: Login functionality

  @Test1
  Scenario: Verify that user is able to login with valid credential
    Given User is on login page
    When User tap on sign in with google button
    And User login into the application with email as "ayesha.mulla@coditas.com" and password as "Strangers@123"
    And User clicks on next button
    Then Verify user navigates to dashboard page by title as "Dashboard"






