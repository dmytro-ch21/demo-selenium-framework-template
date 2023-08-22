package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract void loadPage();

}
