package Utils;

import Base.Setup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Base.Setup.*;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            log.info("Chrome Browser is Started");
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
            log.info("Mozila Browser is Started");
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);
            log.info("Edge Browser is Started");
        } else {
            throw new IllegalArgumentException("Invalid browser specified");
        }

        return driver;
    }
}
