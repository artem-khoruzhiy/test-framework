package pages.catalog;

import component.catalog.FromToFilter;
import component.catalog.Product;
import config.Browser;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class GoodPage extends BaseCatalogPage {

    private By priceFilterLocator = By.xpath("//*[@class='schema-filter__fieldset' and .//*[contains(., 'Минимальная цена')]]");
    private By header = By.tagName("h1");
    private By productLocator = By.className("schema-product__group");

    private By productsLocator = By.id("schema-products");

    private FromToFilter priceFilter;

    public GoodPage(Browser browser) {
        super(browser);
        this.priceFilter = new FromToFilter(browser, browser.getWebDriver().findElement(priceFilterLocator));
    }

    public String getHeaderText() {
        return browser.getWebDriver().findElement(header).getText();
    }

    @Step("Filter by price from {0} to {1}")
    public GoodPage filterByPrice(Double from, Double to) {
        priceFilter.setLimits(from, to);
        waitForFilteringToBeFinished();

        return this;
    }

    @Step("Get all products")
    public List<Product> getProducts() {
        return browser.getWebDriver()
                .findElements(productLocator)
                .stream()
                .map(e -> new Product(browser, e))
                .collect(Collectors.toList());
    }

    public void waitForFilteringToBeFinished() {
        ExpectedCondition<Boolean> listProcessing = ExpectedConditions.attributeContains(productsLocator, "class", "schema-products_processing");
        browser.getWaiter(10, 1).until(listProcessing);
        browser.getWaiter(10, 1).until(ExpectedConditions.not(listProcessing));
    }

}
