package Modules;

import Base.Setup;
import Utils.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.io.File;

import static UIObjects.CommonObjects.*;
import static UIObjects.Lookup_PageObjects.*;
import static Utils.Actions.*;
import static Utils.Constants.*;
import static Utils.ExtentReportListener.test;

public class Lookup_Page extends Setup {

    //This Method is Contains Navigate to Lookup Module
    public static void NavigatetoLookupPage() {

        log.info("------------------------"+getCurrentMethodName()+" is Started-------------------------");
        try {
            WebdriverWait("xpath", Lookup);
            click("xpath", Lookup);
            test.log(Status.PASS,"Successfully Redirected to Lookup Module");
            log.info("Successfully Redirected to Lookup Module");
        } catch (Exception e) {
            test.log(Status.ERROR,e.getMessage());
            log.error("Error while redirecting to Lookup Module"+e.getMessage());
            e.getMessage();
        }
    }

    //This Method is Contains Adding New State
    public static void Addnewstate(String value) throws InterruptedException {

        log.info("------------------------"+getCurrentMethodName()+" is Started-------------------------");
        try {
            WebdriverWait("xpath", state);
            click("xpath", state);
            click("xpath", Addstate);
            Enter("Xpath", statefield, Addedinput);
            Enter("xpath", statecode, "Tn");
            click("xpath", SaveButton);
            Thread.sleep(1000);
            if(Source(value)){
                test.log(Status.PASS," Testdata : "+Addedinput+" | Testoutput :  "+gettext("xpath",Toastcontent));
                log.info("Testdata : "+Addedinput+" | Testoutput : "+gettext("xpath",Toastcontent));
                Assert.assertTrue(true);
            }else{
                test.log(Status.FAIL,"Negative Data is Given : Testdata : "+Addedinput+" Testoutput | "+gettext("xpath",Toastcontent));
                log.warn("Negative Data is Given : Testdata : "+Addedinput+" Testoutput | "+gettext("xpath",Toastcontent));
                softAssert.assertTrue(false);
            }

        } catch (Exception e) {
            test.log(Status.DEBUG,e.getMessage());
            log.error(e.getMessage());
            e.getMessage();
        }
        softAssert.assertAll();
    }


    public static void SelectDropDown() throws InterruptedException {

        log.info("------------------------"+getCurrentMethodName()+" is Started-------------------------");
        click("xpath", City);
        click("xpath", AddCity);
        click("xpath", ddfield);
        Dropdown(ddoption);
    }


    public static void SearchBox() throws InterruptedException, AWTException {

        log.info("------------------------"+getCurrentMethodName()+"is Started-------------------------");
        Searchfilter(searchname);

    }

    public static void DownloadSamplefile() throws InterruptedException {

        log.info("------------------------"+getCurrentMethodName()+"is Started-------------------------");
        try{
            if(isFileDownloaded(statefilename)==true){
                test.log(Status.PASS,"File Downloaded Successfully | File Name : " + statefilename);
                Assert.assertTrue(true);
            }else{
                test.log(Status.FAIL,"File is Not Downloaded | File Name : " + statefilename);
                softAssert.fail();
            }
        }catch(Exception e){
            test.log(Status.DEBUG, e.getMessage());
        }

    }

    public static void UploadState() throws InterruptedException, AWTException {

        log.info("------------------------"+getCurrentMethodName()+"is Started-------------------------");
        click("xpath", state);
        UploadFile(Stateuploadfilename);
    }
}
