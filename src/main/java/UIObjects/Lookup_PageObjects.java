package UIObjects;

import org.openqa.selenium.By;

public class Lookup_PageObjects {

    public static String Lookup = "//*[text()='Look up ']";
    public static String state = "//*[text()='State']";
    public static String Addstate = "//*[text()='Add State']";
    public static String statefield= "//*[@name=\"statename\"]";
    public static String statecode = "//*[@name=\"statecode\"]";
    public static String ViewstateButton = "//*[text()='View State']";
    public static String stateAddedSuccessmsg = "State Saved Successfully";
    public static String stateUpdatedSuccessmsg = "State Updated Successfully";
    public static String stateDeletesuccessmsg = "State Deleted Successfully";
    public static String City = "//a[@href='#/form/city']";
    public static String AddCity = "//*[text()='Add City']";
    public static String ddfield = "//*[@name='stateid']";
    public  static By ddoption = By.xpath("//*[@data-ng-repeat='state in liststate']");
    public static By lists = By.xpath("//*[@data-ng-repeat='state in liststate']");
    public static String Stateuploadfilename = "StateUpload.xlsx";


}

