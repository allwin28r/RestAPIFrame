package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.DeletePayload;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends Utils {
	Response res;
	RequestSpecification req;
	ResponseSpecBuilder resspec;
	static String place_id;

	
	TestDataBuild payload=new TestDataBuild();

	@Given("ADD Place detail is formed {string} {string} {string}")
	public void add_place_detail_is_formed(String name, String lang, String address)  throws IOException{
		
		
		req=given().spec(requestSpecification()).body(payload.addPlacePayload(name,lang,address));
		
		
	}
	@When("request is posted as {string} request FOR {string}")
  public void request_is_posted_as_request_for(String method, String resource) {
		
		APIresources ress=APIresources.valueOf(resource);
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
		
		if(method.equalsIgnoreCase("post"))
		 res=req.when().post(ress.getAPIresources());
		else if(method.equalsIgnoreCase("delete"))
			res=req.when().delete(ress.getAPIresources());
		else if(method.equalsIgnoreCase("get"))
			res=req.when().get(ress.getAPIresources());
		
		System.out.println(resource);
		
		
	}
	@Then("Validate if the status code is {int}")
	public void validate_if_the_status_code_is(int int1) {
	    //System.out.println(" I Am OK");
	   assertEquals(res.getStatusCode(), int1);
	    
	}
	@Then("Validte if the {string} is {string}")
	public void validte_if_the_is(String string1, String string2) {
	   
		//String respAsString=res.asString();
		//JsonPath js=new JsonPath(respAsString);
		//assertEquals(js.get(string1).toString(),string2);
		assertEquals(getJsval(res, string1),string2);
		
		//System.out.println("I am the boss");
	}
	
	@Then("verify if the {string} has {string}")
	public void verify_if_the_has(String resource, String expName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 place_id=getJsval(res, "place_id");
		
		req=given().spec(requestSpecification()).queryParam("place_id", place_id);
		request_is_posted_as_request_for("GET", resource);
		String actualname=getJsval(res, "name");
		assertEquals(actualname, expName);
		
		
		
	}
	@Then("verify if the {string} is successful")
	public void verify_if_the_is_successful(String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		/*
		 * res=given().spec(requestSpecification()).body("{\r\n" +
		 * "    \"place_id\":\""+place_id+"\"\r\n" + "}\r\n" +
		 * "").when().post("/maps/api/place/delete/json");
		 */
	    
	   //req=given().spec(requestSpecification()).body(payload.deletePlacepayload(place_id));
	   
	   request_is_posted_as_request_for("POST",resource);

		
	    
	    
	    assertEquals(res.getStatusCode(),200);
	    	}
	
	@Given("Deleteplace payload")
	public void deleteplace_payload() throws IOException {
		place_id=getJsval(res, "place_id");

		   req=given().spec(requestSpecification()).body(payload.deletePlacepayload(place_id));
	    
	}



}
