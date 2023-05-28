package Tests;


//import static Modules.Lookup_Page.UploadFile;
import static Modules.Lookup_Page.*;
import static UIObjects.Lookup_PageObjects.*;
import static Utils.Actions.*;
import static Utils.Constants.*;

import java.awt.AWTException;

import org.testng.annotations.Test;

import Base.Setup;

public class LookupTest extends Setup {

    @Test(dependsOnMethods = {"Tests.LoginTest.TC001_VerifyLoginPage"})
    public static void TC_002_Navigate_to_Lookup() throws InterruptedException {

        NavigatetoLookupPage();
    }

    @Test(dependsOnMethods = {"TC_002_Navigate_to_Lookup"})
    public static void TC_003_Verify_StateisAdded() throws InterruptedException {

        Addnewstate(stateAddedSuccessmsg);
    }

    @Test(dependsOnMethods = {"TC_003_Verify_StateisAdded"})
    public static void TC_004_VerifyActionsinState() throws InterruptedException {

        VerifyActions(firstrow, Addedinput,statefield,updatedinput , stateUpdatedSuccessmsg, stateDeletesuccessmsg, stateMasterDatas);
    }

    @Test(dependsOnMethods = {"TC_004_VerifyActionsinState"})
    public static void TC_005SearchAction() throws InterruptedException, AWTException {
        SearchBox();
    }

    @Test(dependsOnMethods = {"TC_005SearchAction"})
    public static void TC_006UploadFile() throws InterruptedException, AWTException {
        UploadFile(excelPath);
    }

}
