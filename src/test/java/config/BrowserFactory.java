package config;

import config.drivers.ChromeBrowser;
import config.drivers.FirefoxBrowser;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

public class BrowserFactory {

    public static Browser createBrowser(String type) {
        Browser browser;
        if ("chrome".equals(type)) {
            ChromeDriverManager.chromedriver().setup();
            browser = new ChromeBrowser();
        } else {
            FirefoxDriverManager.firefoxdriver().setup();
            browser = new FirefoxBrowser();
        }

        return browser;
    }

}
