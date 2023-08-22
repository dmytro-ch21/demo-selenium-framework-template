package step_definitions;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BrowserFactory;
import utils.Driver;


import java.time.Duration;
/**
 * The Hooks class manages the setup and teardown processes for each test scenario.
 * It initializes and finalizes WebDriver instances, as well as handles failures and screenshot capture.
 */
public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    public WebDriver driver;

    @Before
    public void setUp() {
        logger.info("Initializing WebDriver and setting properties...");
        // Create driver instance
        Driver.getInstance().setDriver(BrowserFactory.createInstance());
        driver = Driver.getInstance().getDriver();
        // Configure WebDriver properties
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        logger.info("WebDriver initialization and configuration complete.");
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Finalizing test execution...");
        // Capture screenshot on failure
        if (scenario.isFailed()) {
            logger.warn("Scenario '{}' failed. Capturing screenshot...", scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) Driver.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        // Remove WebDriver instance
        Driver.getInstance().removeDriver();
        logger.info("WebDriver instance removed and test execution finalized.");
    }
}
