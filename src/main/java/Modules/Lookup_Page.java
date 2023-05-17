package Modules;

import Base.Commonclass;
import PageObjects.Lookup_PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static PageObjects.Login_Pageobjects.homepage;
import static PageObjects.Lookup_PageObjects.*;

public class Lookup_Page extends Commonclass {

    //This Method is Contains Navigate to Lookup Module
    public static void NavigatetoLookupPage() {

        try{
            Thread.sleep(1000);
            click("xpath", Lookup);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //This Method is Contains Adding New State
    public static void Addnewstate() throws InterruptedException {
        try {
            Wait("xpath",state,driver);
            click("xpath", state);
            click("xpath", Addstate);
            Enter("Xpath", statefield, statename);
            Enter("xpath", statecode, "Tn");
            click("xpath", SaveButton);


            if (driver.getPageSource().contains(gettext("xpath", AddedSuccessmsg))) {
                Assert.assertTrue(true, "State Successfully Added");
            }
        } catch (Exception e) {
            Assert.fail("State is not Added");
            e.printStackTrace();
        }

    }


}
