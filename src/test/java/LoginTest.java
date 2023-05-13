import Modules.Loginpage;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void TC001_Login(){

        Loginpage.LogintoApplication();

    }

}
