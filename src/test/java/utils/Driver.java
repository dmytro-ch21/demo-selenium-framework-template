package utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver is a singleton class responsible for managing WebDriver instances.
 * It uses a thread-safe implementation through the Bill Pugh Singleton Design.
 * Each thread has its own instance of the WebDriver, ensuring thread safety in parallel executions.
 */
public class Driver {
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private Driver(){
        logger.info("Initializing Driver instance");
    }

    private static class SingletonHelper{
        private static final Driver INSTANCE = new Driver();
    }
    public static Driver getInstance(){
        return SingletonHelper.INSTANCE;
    }
    public WebDriver getDriver() {
        if (driver.get() == null) {
            logger.warn("Requested WebDriver is not initialized yet");
        }
        return driver.get();
    }
    public void setDriver(WebDriver driverParameter) {
        if (driver.get() != null) {
            logger.warn("Overwriting existing WebDriver instance");
        }
        driver.set(driverParameter);
        logger.info("WebDriver instance set: {}", driverParameter.getClass().getSimpleName());
    }

    public void removeDriver() {
        if (driver.get() != null) {
            logger.info("Closing WebDriver: {}", driver.get().getClass().getSimpleName());
            driver.get().quit();
            driver.remove();
            logger.info("WebDriver instance removed");
        } else {
            logger.warn("No WebDriver instance found to remove");
        }
    }

}
