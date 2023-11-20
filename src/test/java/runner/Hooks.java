package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverFactory;
import utils.GenericUtils;

import java.util.Objects;

/**
 * The Hooks class provides methods to set up and tear down the WebDriver before and after each scenario.
 */
public class Hooks extends GenericUtils {

    /**
     * Sets up the WebDriver before each scenario.
     */
    @Before
    public void setup() {
        DriverFactory.getWebDriver();
    }

    /**
     * Tears down the WebDriver after each scenario. Takes a screenshot if the scenario has failed.
     *
     * @param scenario The Cucumber scenario.
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) Objects.requireNonNull(DriverFactory.getWebDriver())).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
                System.out.println("Unable to take screenshot" + e.getMessage());
            }
        }
        //Objects.requireNonNull(DriverFactory.getWebDriver()).quit();
    }
}

