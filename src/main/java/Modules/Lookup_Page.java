package Modules;

import PageObjects.Lookup_PageObjects;
import org.openqa.selenium.By;
import org.testng.Assert;

import static PageObjects.Lookup_PageObjects.*;

public class Lookup_Page extends Loginpage {

    public static void navigatetoLookupPage(){

        driver.findElement(By.xpath(Lookup_xpath)).click();

    }

    public static void Addnewstate(){

        driver.findElement(By.xpath(state_xpath)).click();
        driver.findElement(By.xpath(Addstate_xpath)).click();
        driver.findElement(By.xpath(statefield_xpath)).sendKeys("Tamilnaduss");
        driver.findElement(By.xpath(statecode_xpath)).sendKeys("Tn");
        driver.findElement(By.xpath(SaveButton_xpath)).click();

        try{
            if (driver.getPageSource().contains(Successmsg_xpath)) {

                Assert.assertTrue(true);
                System.out.println("State Succesfully Added");
            }

        }catch (Exception e){

            e.getMessage();
        }








    }


}
