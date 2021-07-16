package allActivities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProjectActivity {
	
	RequestSpecification reqSpec;
	  String sshKey;
	  int id;
	  
  @BeforeClass
	public void Setup() {
	        
	  reqSpec=new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://api.github.com")
			  .addHeader("Authorization", "").build();
	  
	  sshKey="";
	  }
  
  
  @Test(priority=1)
  public void PostReq() {
	  
	  String reqBody="{\"title\": \"TestAPIKey\", \"key\": \""+sshKey +"\"}";
	  
	  Response response=given().log().all().spec(reqSpec)
			            .when().body(reqBody).post("/user/keys");
	  
	  System.out.println(response.asPrettyString());
	  
	  id=response.then().extract().path("id"); 
	 
	  
	  //Assertions
	  //response.then().body("code", equalTo(201));
	  
  }
  
  @Test(priority=2)
  public void GetReq() {
	  
	  Response response= given().log().all().spec(reqSpec).
			             when().get("/user/keys");
	  
	  System.out.println(response.asPrettyString());
	  
	 //response.then().body("code", equalTo(200));
  }
  
  @Test(priority=3)
  public void DELReq() {
	  
	  Response response=given().log().all().spec(reqSpec).pathParam("keyId",id)
			            .when().delete("/user/keys/{keyId}");
	  
	  System.out.println(response.asPrettyString());
	//  response.then().body("code", equalTo(204));
			  
  }
}
