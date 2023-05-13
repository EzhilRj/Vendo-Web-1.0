package Modules;

import PageObjects.Login_Pageobjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Loginpage {

    public static WebDriver driver;

    public static void LogintoApplication(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("http://testvendo.ppms.co.in/#/page/signin");
        driver.findElement(By.id(Login_Pageobjects.username_ID)).sendKeys("Vendoadmin");
        driver.findElement(By.id(Login_Pageobjects.password_ID)).sendKeys("Vendo@2022");
        driver.findElement(By.xpath(Login_Pageobjects.Login_Xpath)).click();


    }


}
