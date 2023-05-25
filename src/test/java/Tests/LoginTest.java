package Tests;

import Base.Setup;
import Utils.XLUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static Modules.Loginpage.LogintoApplication;
import static Utils.XLUtils.sheet;


public class LoginTest extends Setup {

    @Test
    public static void TC001_VerifyLogin() throws InterruptedException, IOException, NoSuchFieldException {

        LogintoApplication();


    }

}


