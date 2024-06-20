Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met

    Background:
      Given the user is on the Change Password screen

    Scenario: Successfully change password
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "NewPassword123" as the new password
      And the user confirms "NewPassword123" as the new password
      And the user submits the change password request
      Then the password should be successfully changed
      And the user should see a confirmation message "Your password has been changed successfully"


    Scenario: New password and confirmation do not match
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "NewPassword123" as the new password
      And the user confirms "DifferentPassword123" as the new password
      And the user submits the change password request
      Then the password change should fail
      And the user should see an error message "The new password and confirmation password do not match"


    Scenario: New password does not meet the minimum length of characters requirements
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "pa" as the new password
      And the user confirms "pa" as the new password
      And the user submits the change password request
      Then the password change should fail
      And the user should see an error message "The new password does not meet the minimum length of characters requirements"

    Scenario: New password does not meet at least one digit requirements
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "password" as the new password
      And the user confirms "password" as the new password
      And the user submits the change password request
      Then the password change should fail
      And the user should see an error message "The new password does not meet at least one digit requirements"


    Scenario: Current password is incorrect
      Given the current username is "testuser"
      And the current password is "WrongPassword123"
      When the user enters "NewPassword123" as the new password
      And the user confirms "NewPassword123" as the new password
      And the user submits the change password request
      Then the password change should fail
      And the user should see an error message "The current password is incorrect"


    Scenario: New password is same as the current password
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "OldPassword123" as the new password
      And the user confirms "OldPassword123" as the new password
      And the user submits the change password request
      Then the password change should fail
      And the user should see an error message "The new password cannot be the same as the current password"

    Scenario: Cancel password change
      Given the current username is "testuser"
      And the current password is "OldPassword123"
      When the user enters "NewPassword123" as the new password
      And the user confirms "NewPassword123" as the new password
      And the user cancels the change password request
      Then the password should not be changed
      And the user should be redirected to the previous screen
