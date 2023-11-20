package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * The DriverFactory class provides methods for initializing and managing WebDriver instances.
 */
public class DriverFactory {
    private static WebDriver driver;

    /**
     * Retrieves the WebDriver instance. If it doesn't exist, initializes a new one based on the specified browser type.
     *
     * @return The WebDriver instance.
     */
    public static WebDriver getWebDriver() {
        if (driver != null) {
            return driver; // Return the existing driver instance if it already exists
        }

        Properties properties = new Properties();
        FileInputStream configFile = null;

        try {
            configFile = new FileInputStream(Constants.PROPERTIES_CONFIG_DIRECTORY);
            properties.load(configFile);

            String browserType = properties.getProperty("Browser");

            if ("chrome".equalsIgnoreCase(browserType)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("firefox".equalsIgnoreCase(browserType)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("edge".equalsIgnoreCase(browserType)) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else {
                System.out.println("Invalid browser type specified in the properties file.");
            }
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (IOException e) {
            System.out.println("Error reading properties file: " + e.getMessage());
            return null;
        } catch (WebDriverException e) {
            System.out.println("WebDriver exception: " + e.getMessage());
            return null;
        } finally {
            if (configFile != null) {
                try {
                    configFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

