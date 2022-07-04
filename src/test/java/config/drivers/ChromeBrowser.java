package config.drivers;

import config.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser extends Browser {

    public ChromeBrowser() {
        createDriver();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        super.driver = new ChromeDriver(options);
    }
}
