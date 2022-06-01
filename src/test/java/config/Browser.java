package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

public abstract class Browser {

    private Logger logger = Logger.getLogger(getClass());

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
        logger.info(url + " opened");
        driver.get(url);
    }

    public Actions getActions() {
        return new Actions(driver);
    }

}
