package listener;

import config.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Browser browser = (Browser)result.getTestContext().getAttribute("browser");
        TakesScreenshot t = (TakesScreenshot) browser.getWebDriver();

        File srcFile = t.getScreenshotAs(OutputType.FILE);
        String fileName = "./screenshot/"
                + result.getName()
                + Arrays.toString(result.getParameters())
                + result.getEndMillis()
                + ".jpg";
        try {
            FileUtils.copyFile(srcFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
