package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.CartPage;
import pages.LoginPage;
import pages.OverviewPage;
import pages.ProductsPage;
import utils.ConfigReader;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OverviewSteps {

    LoginPage loginPage = new LoginPage();

    ProductsPage productsPage = new ProductsPage();

    CartPage cartPage = new CartPage();

    OverviewPage overviewPage = new OverviewPage();

    private double itemPrice;

    @Given("Given I login to the Swag Labs website")
    public void i_login_to_the_swag_labs_website() {
        loginPage.loadPage();
        loginPage.login(
                ConfigReader.getInstance().getProperty("config.properties", "login.username"),
                ConfigReader.getInstance().getProperty("config.properties", "login.password")
        );
        itemPrice = productsPage.getItemPrice();
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.clickOnCheckoutButton();
    }

    @Then("the item total amount should be correct")
    public void the_item_total_amount_should_be_correct() {
        BigDecimal expectedItemPrice = BigDecimal.valueOf(itemPrice);
        BigDecimal actualItemPrice = BigDecimal.valueOf(overviewPage.getSummarySubtotal());
        BigDecimal summaryTax = BigDecimal.valueOf(overviewPage.getSummaryTax());
        // Summing up the expected item price and tax
        BigDecimal expectedTotal = expectedItemPrice.add(summaryTax).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualTotal = BigDecimal.valueOf(overviewPage.summaryTotal());
        Assert.assertEquals("Item price validation failed.", 0, expectedItemPrice.compareTo(actualItemPrice));
        Assert.assertEquals("Total price validation failed.", 0, expectedTotal.compareTo(actualTotal));
    }

    @Then("I click on finish button on checkout page")
    public void i_click_on_finish_button_on_checkout_page() {
        overviewPage.clickOnFinishCheckoutButton();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        String actualMessage = overviewPage.getConfirmationMessage();
        Assert.assertEquals("Checkout wasn't completed, order was not placed", expectedMessage, actualMessage);
        Assert.assertTrue("Checkout wasn't completed, order was not placed", actualMessage.contains(expectedMessage));
    }
}
