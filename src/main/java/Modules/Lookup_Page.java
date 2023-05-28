package Modules;

import Base.Setup;
import Utils.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.awt.*;

import static UIObjects.Lookup_PageObjects.*;
import static Utils.Actions.*;
import static Utils.Constants.*;
import static Utils.ExtentReportListener.test;

public class Lookup_Page extends Setup {

    //This Method is Contains Navigate to Lookup Module
    public static void NavigatetoLookupPage() {

        try {
            Thread.sleep(1000);
            click("xpath", Lookup);
            test.log(Status.PASS,"Successfully Redirected to Lookup Module");
        } catch (Exception e) {
            test.log(Status.DEBUG,e.getMessage());
        }
    }

    //This Method is Contains Adding New State
    public static void Addnewstate(String value) throws InterruptedException {
        try {
            Wait("xpath", state, driver);
            click("xpath", state);
            click("xpath", Addstate);
            Enter("Xpath", statefield, Addedinput);
            Enter("xpath", statecode, "Tn");
            click("xpath", SaveButton);
            Thread.sleep(1000);
            if(Source(value)){
                test.log(Status.PASS," Testdata : "+Addedinput+" | Testoutput :  "+gettext("xpath",Toastcontent));
                Assert.assertTrue(true);
            }else{
                test.log(Status.FAIL,"Negative Data is Given : Testdata : "+Addedinput+" Testoutput | "+gettext("xpath",Toastcontent));
                softAssert.assertTrue(false);
            }

        } catch (Exception e) {
            test.log(Status.DEBUG,e.getMessage());
            e.printStackTrace();
        }
        softAssert.assertAll();
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
