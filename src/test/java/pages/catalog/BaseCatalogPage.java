package pages.catalog;

import component.catalog.Navigation;
import config.Browser;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public abstract class BaseCatalogPage {

    private By navigationLocator = By.className("catalog-navigation");

    private Navigation navigation;

    protected Browser browser;

    public BaseCatalogPage(Browser browser) {
        this.browser = browser;
        this.navigation = new Navigation(browser, browser.getWebDriver().findElement(navigationLocator));
    }

    @Step("Open catalog. Category: {0}, subcategory {1}, product: {2}")
    public GoodPage openCatalog(String category, String subcategory, String product) {
        navigation.openCatalog(category, subcategory, product);

        return new GoodPage(browser);
    }

}
