package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber-report/cucumber.json"
        },
        features = "@target/rerun.txt",
        glue = "step_definitions"
)
public class FailedScenariosRunner {
}
