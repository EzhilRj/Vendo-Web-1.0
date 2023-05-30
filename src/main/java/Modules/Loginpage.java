package Modules;

import Base.Setup;
import com.aventstack.extentreports.Status;
import freemarker.core._ArrayEnumeration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static UIObjects.CommonObjects.AlertContent;
import static UIObjects.Login_Pageobjects.*;
import static Utils.Actions.*;
import static Utils.ExtentReportListener.test;
import static Utils.XLUtils.*;


public class Loginpage extends Setup {

    public static void LogintoApplication() throws InterruptedException, IOException, NoSuchFieldException {

        log.info("--------------------"+getCurrentMethodName()+ " Method is Started --------------------");

        try {
            List<Map<String, String>> loginDetails = readWriteExcel("Login");
            for (Map<String, String> login : loginDetails) {

                Enter("id", username, login.get("username"));
                Enter("id", password, login.get("password"));
                click("XPATH", Loginbutton);
                Thread.sleep(600);
                if (driver.getPageSource().contains(Mainhome)) {
                    Thread.sleep(1000);
                    test.log(Status.PASS,  " TestData : USERNAME : " + login.get("username") + " :" + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+Lgsuccessmsg);
                    log.warn(" TestData : USERNAME : " + login.get("username") + " :" + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+Lgsuccessmsg);
                    Assert.assertTrue(true);

                    break;
                } else if (Source("Alert")) {
                    test.log(Status.FAIL,  Lgfailedmsg+" | Cause : Negative Data is Given | TestData : USERNAME : " + login.get("username") + " : " + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+gettext("Xpath", AlertContent));
                    log.warn(" TestData : USERNAME : " + login.get("username") + " :" + "PASSWORD : " + login.get("password")+" | "+ "TestOutput : "+" | "+gettext("Xpath", AlertContent));
                    click("XPATH", GotitButton);
                    softAssert.fail(Lgfailedmsg);
                }
            }
        }catch (Exception e) {
            test.log(Status.ERROR,e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();

        }
        softAssert.assertAll();
    }

}


