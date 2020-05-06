package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;

public class TestDataBuild {

	public AddPlace addPlacePayload( String name, String language, String address) {

		//add values to strings and double variables
		pojo.AddPlace plc = new pojo.AddPlace();
		plc.setAccuracy(50);
		plc.setAddress(address);
		plc.setLanguage(language);
		plc.setPhone_number("(+91) 983 893 3937");
		plc.setWebsite("https://rahulshettyacademy.com/");
		plc.setName(name);
		
		// add values to types LIST
		List<String> myTypeList = new ArrayList<String>();
		myTypeList.add("shoe park");
		myTypeList.add("shop");
		
		plc.setTypes(myTypeList);
	
		//add values to location 
		pojo.Location loc = new pojo.Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		plc.setLocation(loc);
		
		return plc;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
