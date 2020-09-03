package learning;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class test {
	@BeforeTest
	public void initialize() {
		RestAssured.baseURI = "https://reqres.in/";
	}

	@Test
	public void ReqresGetRequest() {

		RequestSpecification httpRequest = RestAssured.given();

		Response reponse = httpRequest.when().get("api/users/2");

		reponse.prettyPeek();

	}

}
