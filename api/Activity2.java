package allActivities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2 {
	
	String BASE_URI="https://petstore.swagger.io/v2/user";
	
  @Test(priority=1)
  public void PostReq () throws IOException{
	 
      //FileInputStream inputJSON= new FileInputStream("src/test/java/allActivities/Activity3.json");
      String reqBody= new String(Files.readAllBytes(Paths.get("src/test/java/allActivities/Activity2.json")));
	  
	  Response response= given().contentType(ContentType.JSON).body(reqBody).
			            when().post(BASE_URI);
	  
	  System.out.println(reqBody.toString());
	  System.out.println(response.asPrettyString());
	  
	  response.then().body("code", equalTo(200));
	  
  }
  
  @Test(priority=2)
  public void getReq() {
	  
	  Response response= given().contentType(ContentType.JSON).pathParam("username", "CPBATRA")
			             .when().get(BASE_URI+"/{username}");
	  
	  System.out.println(response.asPrettyString());
	  
	  //Assertions
	  response.then().body("id", equalTo(29121992));
	  response.then().body("username", equalTo("CPBATRA"));
	  response.then().body("firstName", equalTo("Justin"));
	  response.then().body("lastName", equalTo("Case"));
	  response.then().body("email", equalTo("justincase@mail.com"));
	  response.then().body("password", equalTo("password123"));
	  
  }
  
  @Test(priority=3)
  public void delReq() {
	  
	  Response response= given().contentType(ContentType.JSON).pathParam("username", "nikita")
			             .when().delete(BASE_URI+"/{username}");
	  
	  System.out.println(response.asPrettyString());
	  
	  //Assertions
	  response.then().body("code", equalTo(200));
  }
}
