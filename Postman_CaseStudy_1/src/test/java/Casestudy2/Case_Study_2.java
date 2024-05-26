package Casestudy2;

import static io.restassured.RestAssured.*;

import Reusable.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Case_Study_2 {
    @Test
    public void putCall() {

        RequestSpecification re = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON).build();

        RequestSpecification res = given().log().all().spec(re).body(Payload.putData("2129926", "AKASH"));

        String response = res.
                when().post("/posts")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String title = js.get("title");
        String body = js.get("body");
        Assert.assertEquals(title,"2129926");
        Assert.assertEquals(body,"AKASH");

    }
    @Test
    public  void getCall(){
        RequestSpecification re = new RequestSpecBuilder().setBaseUri("http://216.10.245.166/")
                .setContentType(ContentType.JSON).build();

        RequestSpecification res = given().log().all().spec(re).queryParam("AuthorName","John");
        String response = res.
                when().get("Library/GetBook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String book = js.get("[0].book_name");
        System.out.println(book);
    }
}
