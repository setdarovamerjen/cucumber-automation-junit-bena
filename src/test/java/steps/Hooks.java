package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {

    final static Logger logger =Logger.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario){
        logger.info(scenario + "is starting");
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()){
            logger.error(scenario + " failed!");
          final byte [] screenshot= ((TakesScreenshot)(Driver.getDriver())).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }else {
            logger.info(scenario + " passed!");
        }

        Driver.quit();
    }


}
