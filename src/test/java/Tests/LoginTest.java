package Tests;

import Base.Setup;
import Utils.XLUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static Modules.Loginpage.LogintoApplication;
import static Utils.Constants.ExcelPath;

public class LoginTest extends Setup {

    @Test(dataProvider="LoginData")
    public static void TC001_Login(String username , String password) throws InterruptedException {

        LogintoApplication(username,password);

    }

    @DataProvider(name="LoginData")
    public Object[][] getData() throws IOException {

        XLUtils xlutil=new XLUtils(ExcelPath);

        int totalrows=xlutil.getRowCount("Login");
        int totalcols=xlutil.getCellCount("Login",1);

        String loginData[][]=new String[totalrows][totalcols];

        for(int i=1;i<=totalrows;i++) //1
        {
            for(int j=0;j<totalcols;j++) //0
            {
                loginData[i-1][j]=xlutil.getCellData("Login", i, j);
            }

        }

        return loginData;
    }

    }


