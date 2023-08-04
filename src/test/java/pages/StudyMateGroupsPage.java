package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateGroupsPage {
    public StudyMateGroupsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//button[.='Create group']")
    public WebElement createGroupButton;

    @FindBy(name = "name")
    public WebElement nameInput;

    @FindBy(xpath = "//textarea[@type='text']")
    //div//textarea[@name='description']
    public WebElement descriptionInput;

    @FindBy( xpath = "//button[.='Create']")
    public WebElement createBtn;

    @FindBy( xpath = "//div/input[@placeholder]")
    public WebElement dateBtn;


}
