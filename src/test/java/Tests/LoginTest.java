package Tests;

import static Modules.Loginpage.*;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.Setup;


public class LoginTest extends Setup {

    @Test
    public static void TC001_VerifyLoginPage() throws InterruptedException, IOException, NoSuchFieldException {

        LogintoApplication();


    }

}


