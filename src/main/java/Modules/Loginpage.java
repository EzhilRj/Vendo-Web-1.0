package Modules;

import Base.Commonclass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import static PageObjects.Login_Pageobjects.*;
import static Utils.ExtentReportListener.test;

public class Loginpage extends Commonclass {

    public static void LogintoApplication() throws InterruptedException {

        Enter("id", username, "vendoadmin");
        Enter("id", password, "Vendo@2022");
        click("XPATH", Loginbutton);
        Wait("CLASSNAME",homepage,driver);
        try{
            if(driver.getPageSource().contains(homepage)){
                test.log(Status.PASS,"Admin Logged in Successfully");
                Assert.assertTrue(true);
            }else{
                test.log(Status.FAIL,"Admin Login Failed | Cause : " + gettext("xpath","//*[@id=\"dialogContent_2\"]/div/p"));
                Assert.assertTrue(false);
            }

        }catch (Exception e){

            System.out.println(e.getMessage());

        }


    }

}
