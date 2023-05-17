package Modules;

import Base.Commonclass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

import static PageObjects.Login_Pageobjects.*;
import static Utils.ExtentReportListener.test;

public class Loginpage extends Commonclass {

    public static void LogintoApplication(String uname , String pword) throws InterruptedException {

        Enter("id", username, uname);
        Enter("id", password, pword);
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
