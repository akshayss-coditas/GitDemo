package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.GenericUtils;

public class DashboardSteps extends GenericUtils {

    @Given("User is on Dashboard page")
    public void user_is_on_dashboard_page() {
        launchUrl("http://www.coditas.com");
    }
    @When("User tap on sign in")
    public void user_tap_on_sign_in() {
        Assert.assertEquals("AKSHAY","AKSH");
    }

}
