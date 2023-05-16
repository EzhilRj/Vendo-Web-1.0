package Utils;

import Base.Commonclass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Base.Commonclass.driver;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(Commonclass.options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(Commonclass.options);
        } else {
            throw new IllegalArgumentException("Invalid browser specified");
        }

        return driver;
    }
}
