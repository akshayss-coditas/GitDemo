package utils;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * The GenericUtils class provides utility methods for common actions in Selenium WebDriver testing.
 */
public class GenericUtils {
    WebDriver driver = DriverFactory.getWebDriver();

    /**
     * Navigates the WebDriver to the specified URL.
     *
     * @param url A String representing the URL to be launched.
     */
    public void launchUrl(String url) {
        driver.navigate().to(url);
    }

    /**
     * Reads a property value from the configuration file.
     *
     * @param key A String representing the key for which the value needs to be fetched.
     * @return A String representing the value associated with the given key.
     * @throws IOException If an I/O error occurs while reading the properties file.
     */
    public String fetchProperties(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream configFile = new FileInputStream(Constants.PROPERTIES_CONFIG_DIRECTORY);
        properties.load(configFile);
        return properties.getProperty(key);
    }

    /**
     * Waits for an element to be clickable and then clicks on it.
     *
     * @param element The WebElement to be clicked.
     */
    public void waitAndClick(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Uses JavaScript Executor to click on an element.
     *
     * @param element The WebElement to be clicked.
     */
    public void jsClick(WebElement element) {
        waitForElementToBeClickable(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Waits for an element to be visible and enters the specified text into it.
     *
     * @param element The WebElement where text needs to be entered.
     * @param text    A String representing the text to be entered.
     */
    public void waitAndEnterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.sendKeys(text);
    }

    /**
     * Scrolls the page so that the specified element comes into view.
     *
     * @param element The WebElement to be scrolled into view.
     */
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Waits for an element to be visible on the page.
     *
     * @param element The WebElement to wait for.
     */
    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param element The WebElement to wait for.
     */
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Switches the WebDriver focus to the child window.
     * Assumes two windows are open, the parent and the child.
     */
    public void switchToChildWindow() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);
    }

    /**
     * Switches the WebDriver focus back to the parent window.
     * Assumes two windows are open, the parent and the child.
     */
    public void switchToParentWindow() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        driver.switchTo().window(parentWindow);
    }

    /**
     * Verifies that the text of a specified WebElement matches the expected message.
     *
     * @param element       The WebElement whose text needs to be verified.
     * @param actualMessage A String representing the expected text.
     */
    public void verifyToastMessage(WebElement element, String actualMessage) {
        waitForElementToBeVisible(element);
        String message = element.getText();
        Assert.assertEquals(message, actualMessage);
    }

    /**
     * Uploads a file using the specified WebElement and file path.
     *
     * @param element  The WebElement representing the file upload input.
     * @param filePath The path of the file to be uploaded.
     * @throws AWTException if an AWTException occurs
     */
    public void uploadFile(WebElement element, String filePath) throws AWTException {
        // Click the upload element
        waitAndClick(element);

        // Create a new robot instance
        Robot robot = new Robot();

        // Wait for 2 seconds (delay before proceeding)
        robot.delay(2000);

        // Create a StringSelection object containing the file path
        StringSelection ss = new StringSelection(filePath);

        // Set the StringSelection object to the system clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        // Simulate pressing Ctrl+V to paste the file path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        // Simulate pressing Enter to confirm the upload
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


}







