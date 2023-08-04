package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateHomePage {
    WebDriver driver;

    public StudyMateHomePage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[.='Teachers']")
    public WebElement teachersTab;

    @FindBy(xpath = "//li[.='Announcements']")
    public WebElement announcementsTab;

    @FindBy (xpath = "//li[.='Students']")
    public WebElement studentsTab;





}
