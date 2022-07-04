package tests.filtering;

import component.catalog.Product;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.catalog.GoodPage;
import tests.BaseTest;
import utils.CatalogConstants;

import java.util.List;

public class FilterByPriceTests extends BaseTest {

    private double fromPrice = 354.1;
    private double toPrice = 682.9;

    @BeforeClass
    public void openCatalog() {
        browser.open(catalogUrl + CatalogConstants.VACUUM_CLEANER_URL);
    }

    @Description(value = "Filter by price test")
    @Test(testName = "Filter by price test")
    public void filterByPriceTest() {
        GoodPage goodPage = new GoodPage(browser)
                .filterByPrice(fromPrice, toPrice);
        List<Product> products = goodPage.getProducts();
        long filteredCount = products.stream()
                .filter(p -> p.getPrice() >= fromPrice && p.getPrice() <= toPrice)
                .count();

        Assert.assertEquals(filteredCount, products.size(), "Filtering by price works wrong.");
    }

}
