package Modules;

import Base.Setup;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import static PageObjects.Login_Pageobjects.*;
import static Utils.Actions.*;
import static Utils.ExtentReportListener.test;

public class Loginpage extends Setup {

    public static void LogintoApplication(String uname , String pword) throws InterruptedException {

        Enter("id", username, uname);
        Enter("id", password, pword);
        test.log(Status.INFO,"USERNAME : "+uname+ " ; " + "PASSWORD : " +pword);
        click("XPATH", Loginbutton);
        Thread.sleep(1000);

        try {
            if (driver.getCurrentUrl().contains("mainhome")) {
                test.log(Status.PASS, "Admin Logged in Successfully");
                Assert.assertTrue(true);

            } else {
                Wait("xpath", "//*[@aria-label=\"Alert Dialog Demo\"]", driver);
                test.log(Status.WARNING, "Admin Login Failed  | Cause : Negative Data is Given " + gettext("Xpath", "//p[@class='ng-binding']"));
                click("XPATH", GotitButton);
                Assert.assertTrue(false);

            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }

}
