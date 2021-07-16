package allActivities;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity1 {
	
	final static String BASE_URI="https://petstore.swagger.io/v2/pet";
	
  @Test(priority=1)
  public void PostReq() {
	  
	  String reqbody= "{\"id\": 77234,\n" + 
	  		"  \"name\": \"Riley\",\n" + 
	  		"  \"status\": \"alive\"}";
	  
	Response response=given().contentType(ContentType.JSON).body(reqbody).
			          when().post(BASE_URI);
	
	System.out.println(response.asPrettyString());
			            }
  
  @Test(priority=2)
  public void GetReq() {
	  
	  Response response=given().contentType(ContentType.JSON).pathParam("petId", 77234)
			  .when().get(BASE_URI+"/{petId}");
	  System.out.println(response.asPrettyString());
	  
	  //Assertions
	  response.then().body("id", equalTo(77234));
	  response.then().body("name", equalTo("Riley"));
	  response.then().body("status", equalTo("alive"));
	  
	  
	  System.out.println(response.asPrettyString());
	  }

 @Test(priority=3)
	 public void DelReq() {
		 
	 Response response= given().contentType(ContentType.JSON).pathParam("petId", 77234)
			 .when().delete(BASE_URI+"/{petId}");
	 
	 System.out.println(response.asPrettyString());
	 
	 response.then().body("code", equalTo(200));
	 }
 }

