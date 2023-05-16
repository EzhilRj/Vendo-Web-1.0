package Tests;

import Base.Commonclass;
import Modules.Loginpage;
import Utils.RetryAnalyser;
import org.testng.annotations.Test;

import static Modules.Loginpage.LogintoApplication;

public class LoginTest extends Commonclass {
    @Test
    public static void TC001_Login() throws InterruptedException {

        LogintoApplication();

    }

}
