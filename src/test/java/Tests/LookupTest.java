package Tests;


//import static Modules.Lookup_Page.UploadFile;
import static Modules.Lookup_Page.*;
import static UIObjects.CommonObjects.GetMasterDatas;
import static UIObjects.CommonObjects.Rowfirstvalue;
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

        VerifyActions(Rowfirstvalue, Addedinput,statefield,updatedinput , stateUpdatedSuccessmsg, stateDeletesuccessmsg, GetMasterDatas);
    }


    @Test(dependsOnMethods = {"TC_004_VerifyActionsinState"})
    public static void TC_005_Verify_SearchFilter() throws InterruptedException, AWTException {

        SearchBox();
    }

    @Test(dependsOnMethods = {"TC_005_Verify_SearchFilter"})
    public static void TC_006_Verify_SamplefileinState() throws InterruptedException{

        DownloadSamplefile();
    }

    @Test(dependsOnMethods = {"TC_006_Verify_SamplefileinState"})
    public static void TC_007_Verify_UploadState() throws InterruptedException, AWTException {

        UploadState();
    }



}
