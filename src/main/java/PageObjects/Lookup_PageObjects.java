package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

public class Lookup_PageObjects {

    public static String Lookup = "//*[text()='Look up ']";
    public static String state = "//*[text()='State']";
    public static String Addstate = "//*[text()='Add State']";
    public static String fieldname = "//*[@name=\"statename\"]";
    public static String statecode = "//*[@name=\"statecode\"]";
    public static String SaveButton = "//*[@aria-label=\"Save\"]";
    public static String RevertButton = "//*[text()='Revert']";
    public static String ViewstateButton = "//*[text()='View State']";
    public static String AddedSuccessmsg = "//*[text()='State Saved Successfully']";
    public static String UpdatedSuccessmsg = "//*[text()='State Updated Successfully']";
    public static String Deletesuccessmsg = "//*[text()='State Deleted Successfully']";
    public static String TestInput = "//*[@id=\"content\"]/section/div/div[1]/div[2]/section/div[2]/table/tbody/tr[1]/td[2]";
    public static String Editbutton = "//*[@title=\"Edit\"]";
    public static String Deletebutton = "//*[@title=\"Delete\"]";
    public static String YesButton = "//*[text()='Yes']";
    public static String NoButton = "//*[text()='No']";
    public static String Masterstatedatas = "//*[@id=\"content\"]/section/div/div[1]/div[2]/section/div[2]/table/tbody/tr";
    public static String City = "//a[@href='#/form/city']";
    public static String AddCity = "//*[text()='Add City']";
    public static String ddfield = "//*[@name='stateid']";
    public  static By ddoption = By.xpath("//*[@data-ng-repeat='state in liststate']");
    public static By lists = By.xpath("//*[@data-ng-repeat='state in liststate']");
    public static String SampleFileBtn = "//a[@href='images/sampleexcel/state.xlsx']//button[@class='md-raised btn-sm md-primary md-button md-ink-ripple']";
    public static String Upldbtn = "//a[@class='md-raised btn-sm md-primary md-button md-ink-ripple']";
    public static String StateSearch = "//*[@data-ng-keyup='search()']";

}
