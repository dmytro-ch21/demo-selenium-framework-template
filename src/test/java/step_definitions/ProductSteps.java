package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

import java.util.List;

public class ProductSteps {

    LoginPage loginPage = new LoginPage();

    ProductsPage productsPage = new ProductsPage();

    private int initialCartCount;

    private int countBeforeRemoval;

    @Given("I am on the Products Page")
    public void i_am_on_the_products_page() {
        loginPage.loadPage();
        loginPage.login(
                ConfigReader.getInstance().getProperty("config.properties", "login.username"),
                ConfigReader.getInstance().getProperty("config.properties", "login.password")
        );
    }

    @Given("the cart should be empty")
    public void the_cart_should_be_empty() {
        initialCartCount = productsPage.getCartItemCount();
        Assert.assertEquals("The cart is not empty.", 0, initialCartCount);
    }

    @When("I add product with title {string} to the cart")
    public void i_add_a_product_to_the_cart(String productTitle) {
        productsPage.addToCart(productTitle);
    }

    @Then("I verify product is successfully added to the cart")
    public void i_verify_product_is_successfully_added_to_the_cart() {
        int currentCartCount = productsPage.getCartItemCount();
        Assert.assertNotEquals("The product was not added to the cart", initialCartCount, currentCartCount);
    }

    @And("I remove a product from the cart with title {string}")
    public void i_remove_a_product_from_the_cart(String itemTitle) {
        countBeforeRemoval = productsPage.getCartItemCount();
        productsPage.removeItem(itemTitle);
    }

    @Then("the product should be removed")
    public void the_product_should_be_removed() {
        int currentCartCount = productsPage.getCartItemCount();
        Assert.assertNotEquals("The product was not added to the cart", countBeforeRemoval, currentCartCount);
    }

    @And("I sort products by sortType {string}")
    public void i_sort_products_by_sort_type(String sortType) {
        productsPage.sortProducts(sortType);
    }

    @Then("products should be sorted by sortType {string}")
    public void products_should_be_sorted_by_sort_type(String sortType) {
        List<String> displayedProductNames = productsPage.getSortedProductNames();
        switch (sortType) {
            case "Name (A to Z)" ->
                    Assert.assertTrue("Products are not sorted by Name (A to Z)", productsPage.isSortedByNameAsc(displayedProductNames));
            case "Name (Z to A)" ->
                    Assert.assertTrue("Products are not sorted by  Name (Z to A)", productsPage.isSortedByNameDesc(displayedProductNames));
            case "Price (low to high)" ->
                    Assert.assertTrue("Products are not sorted by Price (low to high)", productsPage.isProductsSortedByPriceAsc());
            case "Price (high to low)" ->
                    Assert.assertTrue("Products are not sorted by Price (high to low)", productsPage.isProductsSortedByPriceDesc());
            default -> throw new IllegalArgumentException("Invalid sort type: " + sortType);
        }
    }

    @When("I click on the product {string}")
    public void i_click_on_the_product_name(String attribute) {
        switch (attribute.toUpperCase()) {
            case "NAME" -> productsPage.clickOnTheItemName();
            case "IMAGE" -> productsPage.clickOnTheItemImage();
        }
    }

    @Then("I should redirected to the product detail page")
    public void i_should_redirected_to_the_product_detail_page() {
        Assert.assertTrue(productsPage.isOnProductDetailPage());
    }

    @When("I click open menu button")
    public void i_click_open_menu_button() {
        productsPage.clickOnOpenMenuButton();
    }

    @When("I click on logout")
    public void i_click_on_logout() {
        productsPage.clickOnLogoutButton();
    }

    @Then("I should be able logout successfully")
    public void i_should_be_able_logout_successfully() {
        Assert.assertEquals("Logout validation failed.", (ConfigReader.getInstance().getProperty("config.properties", "base.url")), productsPage.getCurrentUrl());
    }


}
