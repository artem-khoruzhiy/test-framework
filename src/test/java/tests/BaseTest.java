package tests;

import config.Browser;
import config.BrowserFactory;
import listener.TestResultListener;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.PropertiesReader;

@Listeners({TestResultListener.class})
public class BaseTest {

    protected Browser browser;
    protected String catalogUrl = PropertiesReader.getProperty("catalog_url");
    private String browserName = PropertiesReader.getProperty("browser");

    @BeforeClass(description = "Create driver")
    public void setup(ITestContext context) {
        browser = BrowserFactory.createBrowser(browserName);
        context.setAttribute("browser", browser);
    }

    @AfterClass(description = "Close driver", alwaysRun = true)
    public void tearDown() {
        browser.close();
    }
}
