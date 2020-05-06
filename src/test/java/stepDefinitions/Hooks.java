package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deletePlace") // it will run before deletePlace test case which is place_id is null
	public void beforeScenario() throws IOException {
		// get place_id 
		StepDefinitionAddPlace m = new StepDefinitionAddPlace();
		if(StepDefinitionAddPlace.place_id==null) {
		m.add_Place_Payload("Shetty", "French", "Asia");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
	}
		
	}
	
}

