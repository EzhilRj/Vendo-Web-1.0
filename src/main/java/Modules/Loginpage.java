package Modules;

import Base.Setup;
import PageObjects.Login_Pageobjects;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static PageObjects.Login_Pageobjects.*;
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
                test.log(Status.INFO, "USERNAME : " + login.get("username") + " ; " + "PASSWORD : " + login.get("password"));
                Thread.sleep(1000);

                if (driver.getCurrentUrl().contains("mainhome")) {
                    test.log(Status.PASS, "Admin Logged in Successfully");
                    softAssert.assertTrue(true);

                } else {
                    Wait("xpath", "//*[@aria-label=\"Alert Dialog Demo\"]", driver);
                    test.log(Status.WARNING, "Admin Login Failed  | Cause : Negative Data is Given " + gettext("Xpath", "//p[@class='ng-binding']"));
                    click("XPATH", GotitButton);
                    softAssert.fail("Admin login failed.");
                }

            }
        }catch (Exception e) {

        }
        softAssert.assertAll();
    }

}


