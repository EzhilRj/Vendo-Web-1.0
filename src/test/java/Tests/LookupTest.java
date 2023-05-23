package Tests;


import static Modules.Lookup_Page.Addnewstate;
import static Modules.Lookup_Page.NavigatetoLookupPage;
import static PageObjects.Lookup_PageObjects.*;
import static Utils.Actions.VerifyEdit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Base.Setup;

public class LookupTest extends Setup {

    @Test(dependsOnMethods ={"Tests.LoginTest.TC001_Login"})
    public static void TC_002NavigatetoLookup() throws InterruptedException {

        NavigatetoLookupPage();
    }

    @Test(dependsOnMethods = {"TC_002NavigatetoLookup"})
    public static void TC_003VerifystateisAdded() throws InterruptedException {
    	
        Addnewstate();
    }

    @Test(dependsOnMethods = {"TC_003VerifystateisAdded"})
    public static void TC_004VerifyActionsinState() throws InterruptedException {

            VerifyEdit(Teststate,"Tamilnaduss",statefield,"Tamil1",UpdatedSuccessmsg);
    }
}
