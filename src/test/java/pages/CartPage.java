package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.util.List;

public class CartPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> allItems;

    @Override
    public void loadPage() {
        String url = ConfigReader.getInstance().getProperty("config.properties", "base.url") + "cart.html";
        driver.get(url);
        logger.info("Navigated to URL: " + url);
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
        logger.info("Clicked on the checkout button.");
    }

    public void removeProduct(String itemTitle) {
        boolean itemFound = false;
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTitle)) {
                driver.findElement(By.xpath("//div[text() = '" + itemTitle + "']/../..//button")).click();
                logger.info("Element with title " + itemTitle + " successfully found for removal.");
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            logger.warn("Element with title " + itemTitle + " was not found in the cart.");
        }
    }

    public boolean isProductPresent(String productName) {
        for (WebElement item : allItems) {
            if (item.getText().equals(productName)) {
                logger.info("Product named " + productName + " is present in the cart.");
                return true;
            }
        }
        logger.info("Product named " + productName + " is not present in the cart.");
        return false;
    }
}
