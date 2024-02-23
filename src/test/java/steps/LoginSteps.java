package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bson.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.DriverFactory;
import utils.GenericUtils;

public class LoginSteps extends GenericUtils {


    @Given("User is on login page")
    public void user_is_on_login_page() {
        launchUrl("http://www.google.com");
    }

    @When("User tap on sign in with google button")
    public void user_tap_on_sign_in_with_google_button() {


    }

    @When("User login into the application with email as {string} and password as {string}")
    public void user_login_into_the_application_with_email_as_and_password_as(String string, String string2) {

    }

    @When("User clicks on next button")
    public void user_clicks_on_next_button() {
        Assert.assertEquals("tat","jsjs");
    }

    @Then("Verify user navigates to dashboard page by title as {string}")
    public void verify_user_navigates_to_dashboard_page_by_title_as(String string) {

    }


}
