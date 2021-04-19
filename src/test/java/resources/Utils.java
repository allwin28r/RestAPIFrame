package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if (reqSpec==null) {
		
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		 reqSpec=new RequestSpecBuilder().setBaseUri(globalProps("baseURI"))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
		}
		return reqSpec;
	}
	
	public String globalProps(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\xarn7\\Documents\\Allwin\\Rest Assured proj\\Ecli_RA\\FrameworkRSetoe\\src\\test\\java\\resources\\global.propeties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsval(Response res, String key) {
		JsonPath js=new JsonPath(res.asString());
		
		return js.get(key).toString();
		
	}

}
