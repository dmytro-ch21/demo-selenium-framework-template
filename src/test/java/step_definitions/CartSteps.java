package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.ProductsPage;

public class CartSteps {

    ProductsPage productsPage = new ProductsPage();

    CartPage cartPage = new CartPage();

    @And("I navigate to cart")
    public void i_navigate_to_cart() {
        productsPage.clickCartButton();
    }

    @When("I click remove product from the cart with title {string}")
    public void i_click_remove_product_from_the_cart(String title) {
        cartPage.removeProduct(title);
    }

    @Then("the cart should no longer contain product {string}")
    public void the_cart_should_no_longer_contain_product(String expectedProductName) {
        Assert.assertFalse("Product " + expectedProductName + " was not removed from the cart", cartPage.isProductPresent(expectedProductName));
    }

}
