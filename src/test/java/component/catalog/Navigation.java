package component.catalog;

import component.BaseComponent;
import config.Browser;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Navigation extends BaseComponent {

    private By categoryItems = By.xpath("./ul/li");
    private By subCategoryItems = By.cssSelector(".catalog-navigation-list__aside-item");
    private String subCategoryPattern = ".//*[contains(@class, 'catalog-navigation-list__category') and @data-id='%s']";

    private By dropdownItems = By.className("catalog-navigation-list__dropdown-item");
    private By itemTitle = By.className("catalog-navigation-list__dropdown-title");

    private WebElement element;

    public Navigation(Browser browser, WebElement element) {
        super(browser);
        this.element = element;
    }

    public void openCatalog(String category, String subCategory, String product) {
        String dataId = selectCategory(category);
        selectProduct(dataId, subCategory, product);
    }

    @Step("Select category {0}")
    private String selectCategory(String category) {
        WebElement categoryElement = getCategory(category);
        categoryElement.click();

        return categoryElement.getAttribute("data-id");
    }

    @Step("Select product {2}")
    private void selectProduct(String dataId, String subCategoryName, String product) {
        WebElement subCategoryElement = getSubCategoryItem(dataId, subCategoryName);
        browser.getActions().moveToElement(subCategoryElement).perform();
        WebElement productElement = getProductElement(subCategoryElement, product);
        productElement.click();
    }

    private WebElement getCategory(String name) {
        return element.findElements(categoryItems)
                .stream()
                .filter(e -> e.getText().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No category with name " + name));
    }

    private WebElement getSubCategoryItem(String dataId, String name) {
        By locator = By.xpath(String.format(subCategoryPattern, dataId));
        WebElement categoryList = this.element.findElement(locator);
        return categoryList.findElements(subCategoryItems).stream()
                .filter(i -> i.getText().contains(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No sub-category with name " + name));
    }

    private WebElement getProductElement(WebElement subCategoryElement, String name) {
        return subCategoryElement.findElements(dropdownItems).stream()
                .filter(di -> di.findElement(itemTitle).getText().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No product with name " + name));
    }
}
