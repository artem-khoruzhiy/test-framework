package pages.catalog;

import config.Browser;
import org.openqa.selenium.By;

public class GoodPage extends BaseCatalogPage {

    private By header = By.tagName("h1");

    public GoodPage(Browser browser) {
        super(browser);
    }

    public String getHeaderText() {
        logger.info("Getting header");
        return browser.getWebDriver().findElement(header).getText();
    }


}
