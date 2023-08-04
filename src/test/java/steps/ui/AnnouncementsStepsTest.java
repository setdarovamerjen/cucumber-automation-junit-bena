package steps.ui;

import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.StudyMateAnnouncementsPage;
import pages.StudyMateHomePage;

import java.util.List;



public class AnnouncementsStepsTest {
    StudyMateHomePage homePage= new StudyMateHomePage();
    StudyMateAnnouncementsPage announcementsPage= new StudyMateAnnouncementsPage();


    @Then("user navigates to announcements tab")
    public void user_navigates_to_announcements_tab() {
    homePage.announcementsTab.click();
    }

    @Then("verify in announcements page user change the number of results per page")
    public void verify_in_announcements_page_user_change_the_number_of_results_per_page(List<Integer> dataTable) {
      for (Integer number: dataTable){
          announcementsPage.announcementsNumberBox.sendKeys(Keys.chord(Keys.CONTROL + "a"));
          announcementsPage.announcementsNumberBox.sendKeys(number.toString()+ Keys.ENTER);
          Assert.assertEquals((int)number, announcementsPage.listOfAnnouncements.size());
      }

    }

}
