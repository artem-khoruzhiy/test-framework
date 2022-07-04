package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Browser {

    protected WebDriver driver;

    public abstract void createDriver();

    public WebDriver getWebDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }

    public void open(String url) {
        driver.get(url);
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public WebDriverWait getWaiter(int timeout) {
        return new WebDriverWait(driver, timeout);
    }

    public WebDriverWait getWaiter(int timeout, int interval) {
        return new WebDriverWait(driver, timeout, interval);
    }

}
