package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.API_Resources;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefinitionAddPlace extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	 RequestSpecification resssSpec;
	 Response response;
	 TestDataBuild data = new TestDataBuild();
	 static String	place_id; // all test cases will refer the same variable. if not static, will set to null at the end, will be refreshed

	 @Given("Add Place Payload {string} {string} {string}")
	 public void add_Place_Payload(String name, String language, String address) throws IOException {
	
		  resssSpec = given()
//		.log().all()                              ---->>> taken care of Request Spec Builder
//		.queryParam("key", "qaclick123")
				 .spec(requestSpecification() )		
				 .body(data.addPlacePayload(name, language, address));
		}
	
	

	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resource, String http_method) {
		
		//apply ENUM class
		API_Resources resourceAPI = API_Resources.valueOf(resource);
		System.out.println( resourceAPI.getResource() );
		
		//create obect for response (expect ->> keyword)
		 respSpec = new ResponseSpecBuilder()
				 .expectStatusCode(200)
				 .expectContentType(ContentType.JSON).build(); 
		
		if(http_method.equalsIgnoreCase("POST")) { // using enum class
		 response = resssSpec.when().post(resourceAPI.getResource());}
//				.then()
////		.assertThat().statusCode(200)  this statement not needed anymore because we created Response Specification object  
//				.spec(respSpec)
//				.extract().response();
		else if(http_method.equalsIgnoreCase("GET")) {
			 response = resssSpec.when().get(resourceAPI.getResource());
	 }}

	@Then("the response API call got success with status code {string}")
	public void the_response_API_call_got_success_with_status_code(String string) {
	    
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue ) {
		
		Assert.assertEquals(getJsonPath(response, keyValue),expectedValue);
				
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	
			place_id = getJsonPath(response, "place_id");
		
	// prepare req spec
		  resssSpec = given()
				 .spec(requestSpecification() )		
				 .queryParam("place_id", place_id);
		  
		  
		  user_calls_with_http_request(resource,"GET");
		  String actualName = getJsonPath(response, "name");
		  
		  Assert.assertEquals(actualName, expectedName);
	
	}

	@Given("deletePlace Payload")
	public void deleteplace_Payload() throws IOException {
					// mandatory fields comes by this method below -> requestSpecification() 
		resssSpec = 	given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
   }



}
