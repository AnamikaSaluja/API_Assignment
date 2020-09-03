package learning;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class SampleTest {
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

	@Test
	public void ReqresPostRequest() {

		given()
			.header("Content-Type", "application/json")
			.body("{\r\n" + "    \"name\": \"anamika\",\r\n" + "    \"job\": \"leader\"\r\n" + "}")
		.when()
			.post("api/users").
		then()
			.assertThat()
		.statusCode(201).extract().response().prettyPeek();

	}
}
