package steps.ui;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.StudyMateHomePage;
import pages.StudyMateTeachersPage;
import utilities.ApplicationFlow;
import utilities.Driver;

import java.util.List;


public class TeachersStepsTest {

    WebDriver driver= Driver.getDriver();
    StudyMateHomePage HomePage= new StudyMateHomePage();
    StudyMateTeachersPage teachersPage= new StudyMateTeachersPage();


    @Then("user navigates to teachers tab")
    public void user_navigates_to_tab() {
        HomePage.teachersTab.click();
        ApplicationFlow.pause(3000);
        String expectedURL="teachers";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedURL));
    }

    @Then("verify user changes the number of results per page to")
    public void verify_user_changes_the_number_of_results_per_page_to(List <Integer> dataTable) {
        for (Integer number : dataTable){
            teachersPage.numberInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));//we are clearing field
            teachersPage.numberInput.sendKeys(number.toString() + Keys.ENTER);
            ApplicationFlow.pause(3000);
            Assert.assertTrue("ERROR: The number of teachers did not match the expected number",
                    teachersPage.listOfTeachers.size() == number);
        }

    }


}
