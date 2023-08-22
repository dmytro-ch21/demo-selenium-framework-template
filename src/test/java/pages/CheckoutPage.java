package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public class CheckoutPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);

    @FindBy(id = "first-name")
    private WebElement firstNameInputBox;

    @FindBy(id = "last-name")
    private WebElement lastNameInputBox;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInputBox;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @Override
    public void loadPage() {
        String url = ConfigReader.getInstance().getProperty("config.properties", "base.url") + "checkout-step-one.html";
        driver.get(url);
        logger.info("Navigated to URL: " + url);
    }

    public void fillOutFirstName(String firstName) {
        firstNameInputBox.sendKeys(firstName);
        logger.info("Filled out first name with: " + firstName);
    }

    public void fillOutLastName(String lastName) {
        lastNameInputBox.sendKeys(lastName);
        logger.info("Filled out last name with: " + lastName);
    }

    public void fillOutPostalCode(String postalCode) {
        postalCodeInputBox.sendKeys(postalCode);
        logger.info("Filled out postal code with: " + postalCode);
    }

    public void clickContinueButton() {
        continueButton.click();
        logger.info("Clicked on the continue button.");
    }


}

