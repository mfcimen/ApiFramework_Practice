package resources;


import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;  // so the existing log wont be overridden if test run by multiple data more than once 
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqSpec==null) { // so the existing log wont be overridden if test run by multiple data more than once 
			
			// new file will be created
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
								
		//create object of request spec builder  ( SET-->> keyword)
		 reqSpec = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
							//request log
				.addFilter(RequestLoggingFilter.logRequestTo(log))
							// response log
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();	
		 return reqSpec;
		 }
		return reqSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\mfeyy\\eclipse-workspace\\API_Framework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	
	
	public String getJsonPath(Response response, String key) {
	    
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
	

}
