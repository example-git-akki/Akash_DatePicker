package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utilities {

    public static RequestSpecification Request() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(globalsData("BaseUrl"))
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return req;


    }

    public static String globalsData(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\2129926\\OneDrive - Cognizant\\Documents\\API CASE STUDIES\\ak\\Postman_Cucumber\\src\\test\\java\\Resources\\globals.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String AuthBody(String name, String email) {
        String body = "{\n" +
                "   \"clientName\": \"" + name + "\",\n" +
                "   \"clientEmail\": \"" + email + "\"\n" +
                "}";
        return body;

    }

    public static String SubmitBody(String id, String name) {
        String SubmitBody = "{\n" +
                "  \"bookId\": " + id + ",\n" +
                "  \"customerName\": \"" + name + "\"\n" +
                "}";
        return SubmitBody;
    }


    public static String updateName(String updatedName) {
        String upName = "{\r\n  \"customerName\": \"" + updatedName + "\"\r\n}";

        return upName;
    }
}

