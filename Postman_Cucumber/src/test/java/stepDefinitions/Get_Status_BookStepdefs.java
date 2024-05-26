package stepDefinitions;

import Resources.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class Get_Status_BookStepdefs extends Utilities {
    static String accessToken;
    ResponseSpecification resSpec;
    Response response;
    static String orderID;

    static String CustomerNameResponse;

    @When("user calls {string} with Get http request")
    public void userCallsWithGetHttpRequest(String string) throws IOException {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).when().get("/status").then().spec(resSpec).extract().response();
    }

    @Then("the API call got Success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int int1) {
        assertEquals(response.getStatusCode(), 200);
    }


    @And("user validates the {string} in the response body is {string}")
    public void userValidatesTheInTheResponseBodyIs(String keyValue, String expectedValue) {
        String responseValue = response.asString();
        JsonPath js = new JsonPath(responseValue);
        System.out.println(js.get(keyValue).toString());
        assertEquals(js.get(keyValue).toString(), expectedValue);
    }

    @When("user calls {string} with Get List of Books https Request")
    public void userCallsWithGetListOfBooksHttpsRequest(String arg0) throws IOException {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).when().get("/books").then().spec(resSpec).extract().response();
    }

    @And("then user validates the response Body")
    public void thenUserValidatesTheResponseBody() {
        String responseValue = response.asString();
        JsonPath js = new JsonPath(responseValue);
        String data = js.get("name[0]");
        System.out.println(js.get("name[0]").toString());
        Assert.assertEquals(data, "The Russian");
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithBookIDHttpRequest(String arg0, String bookId) throws IOException {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).
                pathParam("bookId", bookId).
                when().get("/books/{bookId}").then().spec(resSpec).extract().response();
    }


    @Then("user extracts the Book Data from the particular BookId")
    public void userExtractsTheBookDataFromTheParticularBookId() {
        String responseValue = response.asString();
        JsonPath js = new JsonPath(responseValue);
        System.out.println("book id is :" + js.get("id"));
        System.out.println("book  name :" + js.get("name"));
        System.out.println("book  author :" + js.get("author"));
        System.out.println("book  type :" + js.get("type"));
        System.out.println("book  price :" + js.get("price"));
        System.out.println("book  current-stock :" + js.get("current-stock"));
        System.out.println("book available is :" + js.get("available"));
    }


    @When("user calls {string} with {string} {string} with post http request")
    public void userCallsWithWithPostHttpRequest(String arg0, String name, String email) throws IOException {
        resSpec = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).body(AuthBody(name, email))
                .when().post("/api-clients/").then().spec(resSpec).extract().response();
    }

    @And("user extracts the Authentication code")
    public void userExtractsTheAuthenticationCode() {
        String responseValue = response.asString();
        JsonPath js = new JsonPath(responseValue);
        System.out.println("Authentication code is :" + js.get("accessToken"));
        accessToken = js.get("accessToken");
    }


    @When("user calls {string} with {string} {string} post Http request and AccessToken")
    public void userCallsWithPostHttpRequestAndAccessToken(String arg0, String id, String name) throws IOException {
        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).headers("Authorization",
                        "Bearer " + accessToken)
                .body(SubmitBody(id, name))
                .when().post("/orders").then().spec(resSpec).extract().response();
    }


    @Then("user extracts the orderId")
    public void userExtractsTheOrderId() throws IOException {
        String responseValue = response.asString();
        JsonPath js = new JsonPath(responseValue);
        orderID = js.get("orderId");
        System.out.println("Order id is:" + orderID);
    }


    @Then("user calls {string} with Get http request and Access Token")
    public void userCallsWithGetHttpRequestAndAccessToken(String arg0) throws IOException {
        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).headers("Authorization",
                        "Bearer " + accessToken)
                .when().get("/orders").then().spec(resSpec).extract().response();

    }

    @Then("user collects an particular order using orderId with get http request and AccessToken")
    public void userCollectsAnParticularOrderUsingOrderIdWithGetHttpRequestAndAccessToken() throws IOException {
        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
        response = given().spec(Request()).headers("Authorization",
                        "Bearer " + accessToken).pathParam("orderId", orderID)
                .when().get("/orders/{orderId}").then().spec(resSpec).extract().response();
    }

    @Then("user deletes the order using order id and access token")
    public void userDeletesTheOrderUsingOrderIdAndAccessToken() throws IOException {
        resSpec = new ResponseSpecBuilder().build();
        response = given().spec(Request()).headers("Authorization",
                        "Bearer " + accessToken).pathParam("orderId", orderID)
                .when().delete("/orders/{orderId}").then().spec(resSpec).extract().response();
    }


    @Then("user updates the name {string} using patch http request and accessToken and orderId")
    public void userUpdatesTheNameUsingPatchHttpRequestAndAccessTokenAndOrderId(String upName) throws IOException {
        resSpec = new ResponseSpecBuilder().build();
        response = given().spec(Request()).headers("Authorization",
                        "Bearer " + accessToken).pathParam("orderId", orderID)
                .body(updateName(upName))
                .when().patch("/orders/{orderId}").then().spec(resSpec).extract().response();
    }




}




