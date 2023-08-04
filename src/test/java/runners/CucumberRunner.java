package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/reports/cucumber.html", "json:target/reports/cucumber.json"},
        glue = "steps/ui",
        features = "src/test/resources/features/ui",
        //tags="@getNotes",
        dryRun = false
)

public class CucumberRunner {

}
