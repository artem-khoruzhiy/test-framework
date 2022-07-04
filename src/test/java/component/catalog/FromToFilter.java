package component.catalog;

import component.BaseComponent;
import config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FromToFilter extends BaseComponent {

    private WebElement element;

    private By fromInput = By.cssSelector(".schema-filter__group > .schema-filter-control:nth-child(1) input");
    private By toInput = By.cssSelector(".schema-filter__group > .schema-filter-control:nth-child(2) input");

    public FromToFilter(Browser browser, WebElement element) {
        super(browser);
        this.element = element;
    }

    public void setLimits(Double from, Double to) {
        setFrom(from);
        setTo(to);
    }

    private void setFrom(Double from) {
        if (from != null) {
            WebElement fromElement = element.findElement(fromInput);
            fromElement.clear();
            fromElement.sendKeys(from.toString());
        }
    }

    private void setTo(Double to) {
        if (to != null) {
            WebElement fromElement = element.findElement(toInput);
            fromElement.clear();
            fromElement.sendKeys(to.toString());
        }
    }
}
