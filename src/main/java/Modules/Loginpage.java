package Modules;

import Base.Setup;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static UIObjects.Login_Pageobjects.*;
import static Utils.Actions.*;
import static Utils.ExtentReportListener.test;
import static Utils.XLUtils.*;


public class Loginpage extends Setup {


    public static void LogintoApplication() throws InterruptedException, IOException, NoSuchFieldException {

        try {
            List<Map<String, String>> loginDetails = readWriteExcel("Login");
            for (Map<String, String> login : loginDetails) {
                Enter("id", username, login.get("username"));
                Enter("id", password, login.get("password"));
                click("XPATH", Loginbutton);
                Thread.sleep(1000);

                if (driver.getCurrentUrl().contains(homepage)) {
                    test.log(Status.PASS,  " TestData : USERNAME : " + login.get("username") + " :" + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+Lgsuccessmsg);
                    softAssert.assertTrue(true);

                } else {
                    Wait("xpath", Alert, driver);
                    test.log(Status.FAIL,  Lgfailedmsg+" | Cause : Negative Data is Given | TestData : USERNAME : " + login.get("username") + " : " + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+gettext("Xpath", AlertContent));
                    click("XPATH", GotitButton);
                    softAssert.assertTrue(false,Lgfailedmsg);
                }

            }
        }catch (Exception e) {
            test.log(Status.DEBUG,e.getMessage());
            e.printStackTrace();
        }
        softAssert.assertAll();
    }

}


