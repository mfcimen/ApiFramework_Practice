package resources;
// enum is special class of java which has collection of constants or methods
public enum API_Resources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;
	
	API_Resources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
