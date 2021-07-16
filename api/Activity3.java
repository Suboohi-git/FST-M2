package allActivities;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity3 {
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	@BeforeTest
	public void setUp() {
		
		reqSpec= new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("https://petstore.swagger.io/v2/pet").build();
		
		resSpec=new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
		
	}
	
	@DataProvider
	public Object[][] testDatainfo(){
		
		Object[][] testData = new Object[][] { 
            { 77232, "Riley", "alive" }, 
            { 77233, "Hansel", "alive" }};
            
            return testData;
	
	}
	
	
	
  @Test(priority=1)
  public void POSTReq() {
	  
	  //First Post Request
	  String reqBody1="{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";
	  
	  System.out.println("Request1: ");
	  Response response1= given().log().all().spec(reqSpec).body(reqBody1)
			             .when().post();
	  
	  System.out.println("Response1: ");
	 System.out.println(response1.asPrettyString());
	 
	 //Assertions
	 response1.then().spec(resSpec);
	 
	 //Second Post Request
	 System.out.println("Request2: ");
	 String reqBody2="{\"id\": 77233, \"name\": \"Hansel\", \"status\": \"alive\"}";
	  
	  Response response2= given().log().all().spec(reqSpec).body(reqBody2)
			             .when().post();
	  
	  System.out.println("Response2: ");
	 System.out.println(response2.asPrettyString());
	  
	 //Assertions
	response2.then().spec(resSpec);
	  
  }
  
  @Test(dataProvider="testDatainfo", priority=2)
  public void GETReq(int id, String name, String Status) throws InterruptedException {
	  
	  Response response= given().log().all().spec(reqSpec).pathParam("petId",id).
			             when().get("/{petId}");
	  
	  
	  System.out.println(response.asPrettyString());
	  
	  response.then().spec(resSpec).body("name", equalTo(name));
  }
  
  @Test(dataProvider="testDatainfo",priority=3)
  public void DELReq(int id, String name, String status ) {
	  
	  Response response=given().log().all().spec(reqSpec).pathParam("petId", id)
			            .when().delete("/{petId}");
	  
	  System.out.println(response.asPrettyString());
	  
	  response.then().spec(resSpec);
  }
}
