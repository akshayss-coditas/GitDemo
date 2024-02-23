package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericUtils;
public class Login extends GenericUtils {

    WebDriver driver;
    @FindBy(id = "username")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id="submit")
    WebElement submitBtn;

    public Login (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterUsername(String uName){
        userName.sendKeys(uName);
    }
    public void enterPassword(String pword)
    {
        password.sendKeys(pword);
    }
    public void submitClick()
    {
        submitBtn.click();
    }
}

