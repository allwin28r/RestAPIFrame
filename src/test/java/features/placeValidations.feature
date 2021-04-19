Feature: Validate the place API

@AddPlace @Regression
Scenario Outline: Add place should be successful
Given ADD Place detail is formed "<name>" "<Language>" "<Address>"
When request is posted as "post" request FOR "AddPlaceAPI"
Then Validate if the status code is 200
And Validte if the "status" is "OK"
And verify if the "getPlaceAPI" has "<name>"
#And Deleteplace payload
#And verify if the "deletePlaceAPI" is successful

Examples:
   |name| Language|Address|
   |Aruvi|English|23,George|
#   |Near Forest|French|25,French|

@DeletePlace @Regression
Scenario: Validate the Delete place API
Given ADD Place detail is formed "<name>" "<Language>" "<Address>"
When request is posted as "post" request FOR "AddPlaceAPI"
Then Validate if the status code is 200
And Deleteplace payload
Then request is posted as "post" request FOR "deletePlaceAPI"
And Validte if the "status" is "OK"

Examples:
   |name| Language|Address|
#   |Aruvi|English|23,George|
    |Near Forest|French|25,French|