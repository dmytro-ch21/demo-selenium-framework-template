package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserFactory {
    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    /**
     * Creates a WebDriver instance based on the provided system property "browser".
     * If no value is provided, Chrome is initialized by default.
     *
     * @return WebDriver instance corresponding to the desired browser.
     */
    public static WebDriver createInstance() {
        WebDriver driver = null;
        if (System.getProperty("browser") == null) {
            logger.info("No browser specified. Initializing Chrome as default.");
            driver = new ChromeDriver();
        } else {
            String browserType = System.getProperty("browser").toUpperCase();
            driver = switch (browserType) {
                case "FIREFOX" -> new FirefoxDriver();
                case "EDGE" -> new EdgeDriver();
                case "SAFARI" -> new SafariDriver();
                default -> new ChromeDriver();
            };
        }
        logger.info("Initialized browser: {}", driver.getClass().getSimpleName());
        return driver;
    }

}