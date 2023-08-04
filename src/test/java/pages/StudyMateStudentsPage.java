package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateStudentsPage {
    WebDriver driver;

    public StudyMateStudentsPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy (xpath = "//button[.='Add student']")
    public WebElement addStudentBtn;

    @FindBy (name = "name")
    public WebElement firstNameInput;

    @FindBy (name = "lastName")
    public WebElement lastNameInput;

    @FindBy (name = "phoneNumber")
    public WebElement phoneNumberInput;

    @FindBy (name = "email")
    public WebElement emailInput;

    @FindBy (id = "mui-component-select-groupId")
    public WebElement groupDropDown;

}
