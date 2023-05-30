package Base;

import Utils.WebDriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static Utils.Constants.*;

public class Setup {
    public static WebDriver driver;
    public static WebElement element;

    public static Logger log = Logger.getLogger(Setup.class);
    public static String testSuiteName;
    public static  SoftAssert softAssert = new SoftAssert();
    public static ChromeOptions options;


    @BeforeSuite
    public void Startbrowser(ITestContext context ){
        PropertyConfigurator.configure(LogConfiguration);
        log.info("--------------Browser Started--------------------------------");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",DownloadsfolderPath);
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        testSuiteName = context.getSuite().getName();
        log.info("Test Running------>  "+testSuiteName);
        driver = WebDriverFactory.createWebDriver(Browser);
        driver.navigate().to(URL);
        log.info(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @AfterSuite(enabled = false)
    public void Tearbrowser(){

        driver.quit();
        log.info("--------------Browser Closed--------------------------------");

    }

    public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
