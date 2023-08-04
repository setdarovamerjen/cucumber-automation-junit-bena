package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudymateLoginPage {

    public StudymateLoginPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (name = "email")
    public WebElement emailInput;

    @FindBy (name = "password")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginBtn;

    @FindBy (xpath = "//nav/a[2]")
    public WebElement groups;

}
