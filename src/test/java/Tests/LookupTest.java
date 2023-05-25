package Tests;


import static Modules.Lookup_Page.SelectDropDown;
//import static Modules.Lookup_Page.UploadFile;
import static Modules.Lookup_Page.*;
import static PageObjects.Lookup_PageObjects.*;
import static Utils.Actions.*;
import static Utils.Constants.*;

import Utils.Actions;
import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Base.Setup;

import java.awt.*;
import java.text.CharacterIterator;

public class LookupTest extends Setup {

    @Test(dependsOnMethods = {"Tests.LoginTest.TC001_Login"})
    public static void TC_002NavigatetoLookup() throws InterruptedException {

        NavigatetoLookupPage();
    }

    @Test(dependsOnMethods = {"TC_002NavigatetoLookup"})
    public static void TC_003VerifystateisAdded() throws InterruptedException {

        Addnewstate();
    }

    @Test(dependsOnMethods = {"TC_003VerifystateisAdded"})
    public static void TC_004VerifyActionsinState() throws InterruptedException {

        VerifyActions(TestInput, "Tamilnaduss", fieldname, "Tamil1", UpdatedSuccessmsg, Deletesuccessmsg);
    }

    @Test(dependsOnMethods = {"TC_004VerifyActionsinState"})
    public static void TC_005SearchAction() throws InterruptedException, AWTException {
        SearchBox();
    }

    @Test(dependsOnMethods = {"TC_005SearchAction"})
    public static void TC_006UploadFile() throws InterruptedException, AWTException {
        UploadFile(excelPath);
    }

}
