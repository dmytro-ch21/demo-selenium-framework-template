package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber-report/cucumber.json",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@regression",
        dryRun = false,
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE
)
public class TestRunner {
}
