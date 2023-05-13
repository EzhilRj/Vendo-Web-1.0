import org.testng.annotations.Test;

import static Modules.Lookup_Page.Addnewstate;
import static Modules.Lookup_Page.navigatetoLookupPage;

public class LookupTest {

    @Test
    public static void TC_002NavigatetoLookup(){

        navigatetoLookupPage();

    }

    @Test
    public static void TC_003VerifystateisAdded(){



    }

}
