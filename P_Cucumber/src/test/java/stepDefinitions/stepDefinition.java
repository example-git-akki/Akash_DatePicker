package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestData;
import resources.Utils;


public class stepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestData d = new TestData();

	@Given("Add Place payload")
	public void add_place_payload() throws FileNotFoundException {

		

//				RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		res = given().spec(requestSpecification())
				.body(d.add_place_payload());
	}
	
	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		// Write code here that turns the phrase above into concrete actions
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("maps/api/place/add/json").then().spec(resSpec).extract().response();
	}

	@Then("the API call got Success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions
		String responsevalue = response.asString();
		JsonPath js = new JsonPath(responsevalue);
		assertEquals(js.get(keyValue).toString(), ExpectedValue);
	}

}

