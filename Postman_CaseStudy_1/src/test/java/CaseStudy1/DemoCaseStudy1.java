package CaseStudy1;


import Reusable.Payload;


import static io.restassured.RestAssured.*;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExtentManager;

public class DemoCaseStudy1 {
    RequestSpecification res;


    @Test(priority = 0)
    public void PostCall() {

        res = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").
                setContentType(ContentType.JSON).build();
        RequestSpecification res1 = given().log().all().spec(res).body(Payload.bodyData("2129926", "1991", "AKASH"));

        String response = res1
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String Msg = js.get("Msg");
        System.out.println("The Message is :" + Msg);
        Assert.assertEquals(Msg, "successfully added");
        String id = js.get("ID");
        System.out.println("THe ID is :" + id);
    }
    @Test(priority = 1)
    public void GetCall(){
        RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://reqres.in").setContentType(ContentType.JSON).build();
        RequestSpecification res1 = given().log().all().spec(res);

        String getResponse = res1.
                    when().get("/api/users/2")
                .then().log().all().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath js1 = new JsonPath(getResponse);
        String fName = js1.get("data.first_name");
        String lName = js1.get("data.last_name");
        Assert.assertEquals(fName,"Janet");
        Assert.assertEquals(lName,"Weaver");

    }
}
