package component.catalog;

import config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Navigation {

    private By categoryItems = By.xpath("./ul/li");
    private By subCategoryItems = By.cssSelector(".catalog-navigation-list__aside-list > .catalog-navigation-list__aside-item");
    private String subCategoryPattern = ".//*[contains(@class, 'catalog-navigation-list__category') and @data-id='%s']";

    private By dropdownItems = By.className("catalog-navigation-list__dropdown-item");
    private By itemTitle = By.className("catalog-navigation-list__dropdown-title");

    private Browser browser;
    private WebElement element;

    public Navigation(Browser browser, WebElement element) {
        this.browser = browser;
        this.element = element;
    }

    public void openCatalog(String category, String subCategory, String product) {
        String dataId = selectCategory(category);
        selectProduct(dataId, subCategory, product);
    }

    private String selectCategory(String category) {
        List<String> collect = element.findElements(categoryItems)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        WebElement categoryElement = element.findElements(categoryItems)
                .stream()
                .filter(e -> e.getText().equals(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No category with name " + category));
        categoryElement.click();

        return categoryElement.getAttribute("data-id");
    }

    private void selectProduct(String dataId, String subCategoryName, String product) {
        By locator = By.xpath(String.format(subCategoryPattern, dataId));
        WebElement categoryList = this.element.findElement(locator);
        WebElement subCategoryElement = categoryList.findElements(subCategoryItems).stream()
                .filter(i -> i.getText().contains(subCategoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No sub-category with name " + subCategoryName));

        browser.getActions().moveToElement(subCategoryElement).perform();

        WebElement productElement = subCategoryElement.findElements(dropdownItems).stream()
                .filter(di -> di.findElement(itemTitle).getText().equals(product))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No product with name " + product));

        productElement.click();
    }
}
