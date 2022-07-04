package component.catalog;

import component.BaseComponent;
import config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product extends BaseComponent {

    private By priceLocator = By.cssSelector(".schema-product__price > a");

    private WebElement element;

    public Product(Browser browser, WebElement element) {
        super(browser);
        this.element = element;
    }

    public Double getPrice() {
        String price = element.findElement(priceLocator).getText()
                .replace("от ", "")
                .replace(" р.", "")
                .replace(",", ".");
        return Double.parseDouble(price);
    }
}
