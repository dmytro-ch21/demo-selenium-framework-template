package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;


public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("I land on Swag Labs page")
    public void i_land_on_on_swag_labs_page() {
        loginPage.loadPage();
    }

    @When("I enter a username {string}")
    public void i_enter_a_username(String username) {
        loginPage.fillOutUsername(username);
    }

    @When("I enter a password {string}")
    public void i_enter_a_password(String password) {
        loginPage.fillOutPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickOnLoginButton();
    }

    @Then("I should be able to see the {string} title")
    public void i_should_be_presented_with_the_products_title(String title) {
        Assert.assertEquals("Products title is not displayed", title, loginPage.getProductsTitle());
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String errorMessage) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals("Expected and actual error messages do not match ", errorMessage, actualError);
    }
}