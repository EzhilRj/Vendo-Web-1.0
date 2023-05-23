package Tests;

import Base.Setup;
import Utils.XLUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static Modules.Loginpage.LogintoApplication;


public class LoginTest extends Setup {

    @Test
    public static void TC001_Login() throws InterruptedException {

        LogintoApplication();

    }

    }


