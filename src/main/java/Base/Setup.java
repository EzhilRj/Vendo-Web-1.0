package Base;

import Utils.WebDriverFactory;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static Utils.Constants.*;
import static Utils.ExtentReportListener.test;

public class Setup {
    public static WebDriver driver;
    public static WebElement element;

    protected static Logger log = Logger.getLogger(Setup.class);
    public static String testSuiteName;

    public static  SoftAssert softAssert = new SoftAssert();

    @BeforeSuite
    public void Startbrowser(ITestContext context ){

        testSuiteName = context.getSuite().getName();
        PropertyConfigurator.configure(LogConfiguration);
        WebDriver driver = WebDriverFactory.createWebDriver(Browser);
        driver.navigate().to(URL);
        log.info(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @AfterSuite(enabled = true)
    public void Tearbrowser(){
        driver.quit();
    }
}
