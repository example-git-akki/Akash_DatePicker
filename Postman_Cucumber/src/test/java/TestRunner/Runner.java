package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\2129926\\OneDrive - Cognizant\\Documents\\API CASE STUDIES\\ak\\Postman_Cucumber\\src\\test\\java\\Features",
        plugin = "html:target/htmlReports/cucumber-report.html",
        glue = {"stepDefinitions"}

)
public class Runner {

}




