Feature: Validating Place APIs
//each senario is a test case

@addPlaceAPI @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

	Given Add Place Payload "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "POST" http request
	Then the response API call got success with status code "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name|language|address|
	|Ahouse|	English| World cross center|
#	|Bhouse|	Spanish| Sea cross center |
	
	@deletePlaceAPI @Regression
	Scenario: Verify that Delete Place functionality is working
	
	Given deletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request 
	Then the response API call got success with status code "200"
	And "status" in response body is "OK"