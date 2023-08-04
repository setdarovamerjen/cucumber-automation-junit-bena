package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/reports/cucumber.html", "json:target/reports/cucumber.json"},
        glue = "steps/api",
        features = "src/test/resources/features/api",
        tags="@api",
        dryRun = false
)

public class ApiCucumberRunner {

}
