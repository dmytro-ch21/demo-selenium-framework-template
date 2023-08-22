package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public class OverviewPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(OverviewPage.class);

    @FindBy(className = "summary_subtotal_label")
    private WebElement summarySubtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement summaryTaxLabel;

    @FindBy(css = ".summary_info_label.summary_total_label")
    private WebElement summaryTotalLabel;

    @FindBy(id = "finish")
    private WebElement finishCheckoutButton;

    @FindBy(className = "complete-header")
    private WebElement orderConfirmationMessage;

    @Override
    public void loadPage() {
        String url = ConfigReader.getInstance().getProperty("config.properties", "base.url") + "checkout-step-two.html";
        driver.get(url);
        logger.info("Navigated to Overview Page with URL: " + url);
    }

    public void clickOnFinishCheckoutButton() {
        finishCheckoutButton.click();
        logger.info("Clicked on finish checkout button.");
    }

    public String getConfirmationMessage() {
        String confirmationMsg = orderConfirmationMessage.getText();
        logger.info("Retrieved order confirmation message: " + confirmationMsg);
        return confirmationMsg;
    }

    public double getSummarySubtotal() {
        double subtotal = extractPrice(summarySubtotalLabel.getText());
        logger.info("Retrieved summary subtotal: $" + subtotal);
        return subtotal;
    }

    public double getSummaryTax() {
        double tax = extractPrice(summaryTaxLabel.getText());
        logger.info("Retrieved summary tax: $" + tax);
        return tax;
    }

    public double summaryTotal() {
        double total = extractPrice(summaryTotalLabel.getText());
        logger.info("Retrieved summary total: $" + total);
        return total;
    }

    private double extractPrice(String priceText) {
        try {
            return Double.parseDouble(priceText.substring(priceText.indexOf("$") + 1));
        } catch (Exception e) {
            logger.error("Error extracting price from text: " + priceText, e);
            return 0;
        }
    }

}
