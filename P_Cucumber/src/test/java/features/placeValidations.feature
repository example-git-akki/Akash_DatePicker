Feature: Validating place Api's

Scenario: Verify if place is being Successfully added usinh AddPlaceApi
Given Add Place payload
When user calls "AddPlaceApi" with Post http request
Then the API call got Success with status code 200
And "status" in response body is "OK"
#And "Scope" in response body is "APP"