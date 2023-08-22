package step_definitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class CheckoutSteps {

    LoginPage loginPage = new LoginPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();

    @Given("I login to the Swag Labs website")
    public void i_login_to_the_swag_labs_website() {
        loginPage.loadPage();
        loginPage.login(
                ConfigReader.getInstance().getProperty("config.properties","login.username"),
                ConfigReader.getInstance().getProperty("config.properties","login.password")
        );
    }

    @And("I click on cart")
    public void i_click_on_cart() {
        productsPage.clickCartButton();
    }

    @And("I click on checkout button")
    public void i_click_on_checkout_button() {
        cartPage.clickOnCheckoutButton();
    }

    @When("I enter the first name {string}")
    public void i_enter_the_first_name(String firstName) {
        checkoutPage.fillOutFirstName(firstName);
    }

    @When("I enter the last name {string}")
    public void i_enter_the_last_name(String lastName) {
        checkoutPage.fillOutLastName(lastName);
    }

    @When("I enter postal code {string}")
    public void i_enter_postal_code(String postalCode) {
        checkoutPage.fillOutPostalCode(postalCode);
    }

    @When("I click on continue button")
    public void i_click_on_continue_button() {
        checkoutPage.clickContinueButton();
    }

    @Given("I'm on Checkout: Information page")
    public void im_on_checkout_information_page() {
        checkoutPage.loadPage();
    }
}
