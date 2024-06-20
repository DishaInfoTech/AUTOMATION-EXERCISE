package recruitment.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import recruitment.glue.W;

/**
 * This Runner class effectively configures and runs Cucumber tests
 * using JUnit, specifies where to find feature files and step definitions, defines test execution options,
 * and ensures proper cleanup of resources (particularly WebDriver instances) after test execution completes.
 */

@RunWith(Cucumber.class) //Indicates that JUnit should use Cucumber as the test runner for this class.

//Configures various options for Cucumber execution.
@CucumberOptions(
        dryRun = false,  //When set to false, Cucumber will execute the scenarios and steps as normal.
        monochrome = false,  //When set to false, console output will not be colorized.
        features = {"src/test/resources/tests"}, //Specifies the location of feature files (Cucumber .feature files) to be executed.
        glue = {"recruitment/glue"}, //Specifies the package(s) where Cucumber will look for step definitions (glue code).
        //Specifies the format and destination for the Cucumber execution reports (HTML and JSON formats).
        plugin = {"html:target/cucumber-html/cucumber.html", "json:target/cucumber-json/cucumber.json"},
        tags = "@regression" //This allows selective execution of specific scenarios based on tags.
)
public class Runner {
    //Annotates a method (close()) to be executed after all tests in the class have run.
    @AfterClass
    public static void close() {
        W.close();
    }
}
