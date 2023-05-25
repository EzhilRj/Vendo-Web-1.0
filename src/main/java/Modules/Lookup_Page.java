package Modules;

import Base.Setup;
import Utils.Actions;
import Utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.List;
import java.nio.file.Path;

import static PageObjects.Lookup_PageObjects.*;
import static Utils.Actions.*;
import static Utils.Constants.*;

public class Lookup_Page extends Setup {

    //This Method is Contains Navigate to Lookup Module
    public static void NavigatetoLookupPage() {

        try {
            Thread.sleep(1000);
            click("xpath", Lookup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //This Method is Contains Adding New State
    public static void Addnewstate() throws InterruptedException {
        try {
            Wait("xpath", state, driver);
            click("xpath", state);
            click("xpath", Addstate);
            Enter("Xpath", fieldname, statename);
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


    public static void SelectDropDown() throws InterruptedException {
        click("xpath", City);
        click("xpath", AddCity);
        click("xpath", ddfield);
        Dropdown(ddoption, lists);
    }


    public static void SearchBox() throws InterruptedException, AWTException {
        click("xpath", StateSearch);
        Enter("xpath", StateSearch, searchname);
        String paginationText = driver.findElement(By.xpath(Constants.paginationText)).getText();
        System.out.println(paginationText);
        System.out.println(noofpages);
        log.info(paginationText);
        String tableObj = driver.findElement(By.xpath(Constants.tableObj)).getText();
        if (tableObj.equals(searchname)) {
            System.out.println("Passed : " + searchname);
        } else {
            System.out.println("Failed");
        }
    }

    public static void UploadFile(String excelPath) throws InterruptedException, AWTException {
        click("xpath", state);
        click("xpath", Upldbtn);
        UploadSampleFile(excelPath);
    }
}
