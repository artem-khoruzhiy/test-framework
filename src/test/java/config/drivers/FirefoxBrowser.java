package config.drivers;

import config.Browser;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends Browser {

    public FirefoxBrowser() {
        createDriver();
    }

    @Override
    public void createDriver() {
        this.driver = new FirefoxDriver();
    }
}
