package pages.catalog;

import component.catalog.Navigation;
import config.Browser;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

public abstract class BaseCatalogPage {

    protected Logger logger = Logger.getLogger(this.getClass());

    private By navigationLocator = By.className("catalog-navigation");

    private Navigation navigation;

    protected Browser browser;

    public BaseCatalogPage(Browser browser) {
        this.browser = browser;
        this.navigation = new Navigation(browser, browser.getWebDriver().findElement(navigationLocator));
    }

    public GoodPage openCatalog(String category, String subcategory, String product) {
        navigation.openCatalog(category, subcategory, product);

        return new GoodPage(browser);
    }

}
