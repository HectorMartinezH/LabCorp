package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/features/CheckJobDescriptionData.feature"}, glue = "stepDefinitions")
public class TestRunner extends AbstractTestNGCucumberTests {
}
