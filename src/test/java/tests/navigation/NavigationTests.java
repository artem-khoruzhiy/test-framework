package tests.navigation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.catalog.CatalogPage;
import pages.catalog.GoodPage;
import tests.BaseTest;

public class NavigationTests extends BaseTest {

    @Test(testName = "Catalog navigation test", dataProvider = "goods")
    public void catalogNavigationTest(String category, String subCategory, String good) {
        browser.open(catalogUrl);
        GoodPage goodPage = new CatalogPage(browser)
                .openCatalog(category, subCategory, good);

        Assert.assertEquals(goodPage.getHeaderText(), good, "Wrong good page opened");
    }

    @DataProvider(name = "goods")
    public Object[][] goodList(){
        return new Object[][] {
                {"Электроника", "Аудиотехника", "Умные колонки"},
                {"Компьютеры и сети", "Комплектующие", "SSD"},
                {"Дом и сад", "Кухня", "Смесители"},
                {"Работа и офис", "Офисная мебель", "Сейфы"}
        };
    }

}
