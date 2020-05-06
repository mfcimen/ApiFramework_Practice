package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features"
		,plugin = "json:target/jsonReports/cucumber-report.json"  // to get reports on command line run with mvn test verify
		,glue = {"stepDefinitions"}
//		,tags = "@addPlaceAPI"
		)
public class TestRunner {

}
