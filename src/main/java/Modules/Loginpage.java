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
        click("XPATH", Loginbutton);
        //Wait("CLASSNAME",homepage,driver);
        try{
            if(driver.getPageSource().contains(homepage)){
                test.log(Status.PASS,"Admin Logged in Successfully");
                Assert.assertTrue(true);

            }else{
                Thread.sleep(1000);
                test.log(Status.WARNING,"Admin Login Failed  | Cause : Negative Data is Given " + gettext("xpath","//*[@id=\"dialogContent_5\"]/div"));
                click("XPATH", GotitButton);
                Assert.fail();

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
