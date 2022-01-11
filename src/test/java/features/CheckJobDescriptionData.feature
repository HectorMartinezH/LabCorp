Feature: Careers menu option
  Scenario: Check Career information are correctly displayed
    When The user logs in to careers option from LabCorp portal
     And searches for "Senior Software QA Analyst"
    Then check that in the results is displayed "Senior Software QA Analyst"
     When View the details for "Senior Software QA Analyst"
     Then verify the job title "Senior Software QA Analyst" is displayed
      And verify the job location "Burlington, North Carolina" is displayed
      And verify the job id "21-85987" is displayed
      And verify the job descriptions are correct
     When applying for the job
     Then verify the information of the aplyied job is the correct one
      And return to job search page




