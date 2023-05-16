package Tests;

import Base.Commonclass;
import Modules.Lookup_Page;
import Utils.RetryAnalyser;
import org.testng.annotations.Test;

import static Base.Commonclass.VerifyActions;
import static Modules.Lookup_Page.*;

public class LookupTest extends Commonclass {

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
        VerifyActions();
    }

}
