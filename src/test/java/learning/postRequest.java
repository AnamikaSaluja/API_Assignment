package learning;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postRequest {
	String cookie;
	String jsessionName;
	String jsessionPass;
	
		@BeforeTest
		public void initialize() {
			RestAssured.baseURI = "https://jira.nagarro.com/";
		}

		@Test(priority = 1)
		public void testLogin()
		{
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Content-Type", "application/json")
						.body("{\"username\":\"anamikasaluja\",\"password\":\"Feb@123456789\"}");
			
			Response response  = httpRequest.when().post("/rest/auth/1/session").then().assertThat().statusCode(200).log().all().extract().response();
			
			jsessionName = response.jsonPath().getString("session.name");
			jsessionPass = response.jsonPath().getString("session.value");
			
			cookie = response.jsonPath().getString("session.name") + "=" + response.jsonPath().getString("session.value");
			System.out.println(cookie);
			
		}
			@Test(priority = 2)
			public void getTaskDetails() {
				RequestSpecification httpRequest = RestAssured.given().log().all();

				httpRequest.header("Content-Type", "application/json").auth().preemptive().basic("anamikasaluja", "Feb@123456789").
				pathParam("id","BNSEH-1393");
				
				Response response=httpRequest.when().get("/rest/api/2/issue/{id}").
						then().log().all().assertThat().statusCode(200).extract().response();
				

			}
					
				
		
}
