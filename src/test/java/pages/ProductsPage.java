package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ProductsPage.class);

    @FindBy(xpath = "(//*[@class = 'inventory_item'])[1]//*[@class = 'inventory_item_price']")
    private WebElement firstItemPrice;

    @FindBy(xpath = "(//*[@class = 'btn btn_primary btn_small btn_inventory'])[1]")
    private WebElement firstItemAddProductButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeProductFromPageButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortProductsDropdown;

    @FindBy(className = "inventory_item_description")
    private List<WebElement> allProducts;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> allProductPrices;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(xpath = "//img[@class = 'inventory_item_img']")
    private List<WebElement> inventoryItemImages;

    @FindBy(className = "inventory_item")
    private List<WebElement> allItems;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@class='inventory_item_label']/a")
    private List<WebElement> itemTitles;

    @Override
    public void loadPage() {
        String url = ConfigReader.getInstance().getProperty("config.properties", "base.url") + "inventory.html";
        driver.get(url);
        logger.info("Navigated to Overview Page with URL: " + url);
    }

    public void addToCart(String itemTitle) {
        for (WebElement item : itemTitles) {
            if (item.getText().equals(itemTitle)) {
                driver.findElement(By.xpath("//*[text() = '" + itemTitle + "']/../../..//button")).click();
                logger.info("Element with title " + itemTitle + " successfully added to the cart.");
                return;
            }
        }
        logger.error("No such item title");
    }

    public double getItemPrice() {
        return Double.parseDouble(firstItemPrice.getText().replace("$", ""));
    }

    public void clickCartButton() {
        cartButton.click();
        logger.info("Clicked on the cart button.");
    }

    public void sortProducts(String sortType) {
        Select dropDownSelect = new Select(sortProductsDropdown);
        dropDownSelect.selectByVisibleText(sortType);
        logger.info("Products sorted by: " + sortType);
    }

    public void clickOnTheItemName() {
        inventoryItemNames.get(0).click();
    }

    public void clickOnTheItemImage() {
        inventoryItemImages.get(0).click();
    }

    public void clickOnOpenMenuButton() {
        openMenuButton.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
        logger.info("Clicked on logout button.");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public List<String> getSortedProductNames() {
        return allProducts.stream()
                .map(product -> product.getText())
                .collect(Collectors.toList());
    }

    public boolean isSortedByNameAsc(List<String> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).compareTo(products.get(i + 1)) > 0) {
                logger.debug("Products not sorted in ascending order at: " + products.get(i));
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByNameDesc(List<String> products) { // apple banana
        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).compareTo(products.get(i + 1)) < 0) {
                logger.debug("Products not sorted in descending order at: " + products.get(i));
                return false;
            }
        }
        return true;
    }

    public boolean isProductsSortedByPriceAsc() {
        List<Double> prices = allProductPrices.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
        return isListSortedAsc(prices);
    }

    public boolean isListSortedAsc(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                logger.debug("Products not sorted in ascending order at: " + list.get(i));
                return false;
            }
        }
        return true;
    }

    public boolean isProductsSortedByPriceDesc() {
        List<Double> prices = allProductPrices.stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
        return isListSortedDesc(prices);
    }

    public boolean isListSortedDesc(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                logger.debug("Products not sorted in ascending order at: " + list.get(i));
                return false;
            }
        }
        return true;
    }

    public boolean isOnProductDetailPage() {
        return driver.getCurrentUrl().contains("/inventory-item");
    }

    public int getCartItemCount() {
        try {
            WebElement element = driver.findElement(By.className("shopping_cart_badge"));
            int count = Integer.parseInt(element.getText());
            logger.debug("Cart item count retrieved: " + count);
            return count;
        } catch (Exception e) {
            logger.warn("Failed to retrieve cart item count.");
            return 0;
        }
    }

    public void removeItem(String itemTitle) {
        for (WebElement item : itemTitles) {
            if (item.getText().equals(itemTitle)) {
                WebElement removeButton = driver.findElement(By.xpath("//*[text() = '" + itemTitle + "']/../../..//button"));
                if ("Remove".equals(removeButton.getText())) {
                    removeButton.click();
                    logger.info("Element with title " + itemTitle + " successfully removed from the cart.");
                    return;
                } else {
                    logger.warn("Item with title " + itemTitle + " is not added to the cart. Removal skipped.");
                    return;
                }
            }
        }
        logger.error("No such item with title: " + itemTitle);
    }
}

