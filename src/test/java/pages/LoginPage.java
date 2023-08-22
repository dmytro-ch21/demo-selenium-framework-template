package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @Override
    public void loadPage() {
        String url = ConfigReader.getInstance().getProperty("config.properties", "base.url");
        driver.get(url);
        logger.info("Navigated to Login Page with URL: " + url);
    }

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "title")
    private WebElement productsTitle;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        logger.info("Attempted to log in with username: " + username);
    }
   public void fillOutUsername(String username){
        usernameField.sendKeys(username);
        logger.info("Filled out username with: " + username);
   }

    public void fillOutPassword(String password) {
        passwordField.sendKeys(password);
        logger.info("Clicked on the login button." + "*".repeat(password.length()));
    }

    public void clickOnLoginButton() {
        loginButton.click();
        logger.info("Clicked login button.");
    }

    public String getErrorMessage(){
        String strErrorMessage = errorMessage.getText();
        logger.info("Retrieved error message: " + strErrorMessage);
        return strErrorMessage;
    }

    public String getProductsTitle(){
        String titleText = productsTitle.getText();
        logger.info("Retrieved products title: " + titleText);
        return titleText;
    }

}
