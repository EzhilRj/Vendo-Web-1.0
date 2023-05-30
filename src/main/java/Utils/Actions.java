package Utils;

import Base.Setup;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static UIObjects.CommonObjects.*;
import static UIObjects.Lookup_PageObjects.*;
import static Utils.Constants.*;
import static Utils.ExtentReportListener.test;

public class Actions extends Setup {

    public static String drpName = "TAMIL NADU";
    public static String value;
    public static String Addedinput = "Kanchipuram";
    public static String updatedinput = "Testnaduss1";
    public static String searchname = "TAMIL NADU";
    public static String statefilename = "state.xlsx";
    public static WebElement element;
    public static WebDriverWait wait;

    public static void click(String attributeName, String attributeValue) {

        try {
            String AN = attributeName.toUpperCase();
            switch (AN) {
                case "ID" -> driver.findElement(By.id(attributeValue)).click();
                case "NAME" -> driver.findElement(By.name(attributeValue)).click();
                case "XPATH" -> driver.findElement(By.xpath(attributeValue)).click();
                case "CLASSNAME" -> driver.findElement(By.className(attributeValue)).click();
                case "CSSSELECTOR" -> driver.findElement(By.cssSelector(attributeValue)).click();
                case "TAGNAME" -> driver.findElement(By.tagName(attributeValue)).click();
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }


    public static void Enter(String attributeName, String attributeValue, String inputText) {

        try {
            String AN = attributeName.toUpperCase();
            switch (AN) {
                case "ID" -> {
                    driver.findElement(By.id(attributeValue)).clear();
                    driver.findElement(By.id(attributeValue)).sendKeys(inputText);
                }
                case "NAME" -> {
                    driver.findElement(By.name(attributeValue)).clear();
                    driver.findElement(By.name(attributeValue)).sendKeys(inputText);
                }
                case "XPATH" -> {
                    driver.findElement(By.xpath(attributeValue)).clear();
                    driver.findElement(By.xpath(attributeValue)).sendKeys(inputText);
                }
                case "CLASSNAME" -> {
                    driver.findElement(By.className(attributeValue)).clear();
                    driver.findElement(By.className(attributeValue)).sendKeys(inputText);
                }
                case "CSSSELECTOR" -> {
                    driver.findElement(By.cssSelector(attributeValue)).clear();
                    driver.findElement(By.cssSelector(attributeValue)).sendKeys(inputText);
                }
                case "TAGNAME" -> {
                    driver.findElement(By.tagName(attributeValue)).clear();
                    driver.findElement(By.tagName(attributeValue)).sendKeys(inputText);
                }
                default -> System.out.println("Invalid attribute name specified: " + attributeName + attributeValue);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            e.getMessage();
        }
    }

    public static String gettext(String attributeName, String attributeValue) {


        String AN = attributeName.toUpperCase();
        String text = null;
        switch (AN) {
            case "ID":
                text = driver.findElement(By.id(attributeValue)).getText();
                break;
            case "NAME":
                text = driver.findElement(By.name(attributeValue)).getText();
                break;
            case "XPATH":
                text = driver.findElement(By.xpath(attributeValue)).getText();
                break;
            case "CLASSNAME":
                text = driver.findElement(By.className(attributeValue)).getText();
                break;
            case "CSSSELECTOR":
                text = driver.findElement(By.cssSelector(attributeValue)).getText();
                break;
            case "TAGNAME":
                text = driver.findElement(By.tagName(attributeValue)).getText();
        }

        return text;
    }


    public static void WebdriverWait(String attributeName, String attributeValue) {

        try {
            String AN = attributeName.toUpperCase();
            wait = new WebDriverWait(driver, 10);
            switch (AN) {
                case "ID" -> {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(attributeValue)));
                }
                case "XPATH" -> {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attributeValue)));
                }
                case "CLASSNAME" -> {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(attributeValue)));
                }
                case "CSSSELECTOR" -> {
                    element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attributeValue)));
                }
                default -> System.out.println("Invalid attribute name specified: " + attributeName + attributeValue);
            }
        }catch (Exception e) {
            e.getMessage();
            log.error(e.getMessage());
        }
    }

    public static void VerifyActions(String firstrowvalue, String Addedvalue, String fieldname, String UpdatedValue, String Updsuccessmsg, String Delsuccesmsg, String MasterDatas) throws InterruptedException {

        WebdriverWait("xpath", firstrowvalue);
        value = gettext("xpath", firstrowvalue);
        if (value.equalsIgnoreCase(Addedvalue)) {
            Thread.sleep(1000);
            log.info("Updating the added State");
            click("xpath", Editbutton);
            Enter("xpath", fieldname, updatedinput);
            click("xpath", SaveButton);
            Thread.sleep(1000);
            try {
                if ((Source(stateUpdatedSuccessmsg))) {
                    test.log(Status.PASS, "EDIT : Testdata : " + updatedinput + " | Testoutput : " + gettext("xpath", Toastcontent));
                    Assert.assertTrue(true);
                } else {
                    Thread.sleep(1000);
                    test.log(Status.FAIL, "EDIT : Negative Data is Given | Testdata : " + updatedinput + " | Testoutput : " + gettext("xpath", Toastcontent));
                    softAssert.assertTrue(false);
                }
            } catch (Exception e) {
                test.log(Status.DEBUG, e.getMessage());
                e.printStackTrace();

            }
        }
        //Delete Action takes place here
        try {
            value = gettext("xpath", firstrowvalue);
            if (value.equalsIgnoreCase(updatedinput)) {
                click("xpath", Deletebutton);
                click("xpath", YesButton);
                Thread.sleep(2000);
                if ((Source(stateDeletesuccessmsg))) {
                    test.log(Status.PASS, "DELETE : Testdata : " + updatedinput + " | Testoutput : " + gettext("xpath", Toastcontent));
                    Assert.assertTrue(true);
                } else {
                    Thread.sleep(1000);
                    test.log(Status.FAIL, "DELETE : Negative Data is Given | Testdata :  " + updatedinput + " | Testoutput : " + gettext("xpath", Toastcontent));
                    softAssert.assertTrue(false);
                }
                WebdriverWait("xpath", MasterDatas);
                List<WebElement> datas = driver.findElements(By.xpath(MasterDatas));
                boolean deleted = true;
                log.info("Deleted the added value");
                for (WebElement element : datas) {
                    String value = element.getText();
                    deleted = value.contains(updatedinput);
                    break;
                }
                Assert.assertEquals(deleted, false);
            } else {
                softAssert.assertTrue(false);
            }
        } catch (Exception e) {
            test.log(Status.DEBUG, e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();

    }

    public static void Dropdown(By ddoptions) throws InterruptedException {
        try {

            List<WebElement> dropdownOptions = driver.findElements(ddoptions);
            for (WebElement ele : dropdownOptions) {
                String option = ele.getText();
                if (option.equalsIgnoreCase(drpName)) {
                    ele.click();
                    log.info(drpName + " is selected");
                    break;
                }
            }
        }catch (Exception e){
            log.error( e.getMessage());
            test.log(Status.ERROR,e.getMessage());
        }
    }

    public static void UploadFile(String filename) throws InterruptedException, AWTException {
        try {
            click("xpath", Upldbtn);
            Robot rs = new Robot();
            rs.delay(500);
            StringSelection Filepath = new StringSelection(Uploadfolderpath+filename);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Filepath, null);
            rs.keyPress(KeyEvent.VK_CONTROL);
            rs.keyPress(KeyEvent.VK_V);
            rs.delay(500);
            rs.keyRelease(KeyEvent.VK_CONTROL);
            rs.keyRelease(KeyEvent.VK_V);
            log.info("File Location sent !------->"+Filepath);
            rs.keyPress(KeyEvent.VK_ENTER);
            rs.keyRelease(KeyEvent.VK_ENTER);
            log.info(filename+"  File uploaded successfully");
        }catch (Exception e){
            test.log(Status.ERROR,e.getMessage());
            log.error(e.getMessage());
        }
    }


    @AfterMethod
    public void captureScreen(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target = new File(screenshotPath + result.getName() + ".png");

            FileUtils.copyFile(source, target);
        }

    }

    public static boolean Source(String value) {

        if (driver.getPageSource().contains(value)) {
            return true;
        }
        return false;
    }

    public static void Searchfilter(String Searchvalue) throws InterruptedException, AWTException {
        try {
            Enter("xpath", SearchFiltertxtbox, Searchvalue);
            int rowsize = driver.findElements(By.xpath(Webtablerow)).size();
            if (rowsize == 1) {
                test.log(Status.PASS, "TestData : " + Searchvalue + " | " + "TestOutput : Search Filter is working Properly");
                log.info("TestData : " + Searchvalue);
                Assert.assertEquals(gettext("xpath", Webtablefirstrowdata), Searchvalue);
            } else {
                test.log(Status.FAIL, "Search Filter is Not working Properly");
                log.warn("TestData : " + Searchvalue + "Search Filter is Not working Properly");
                softAssert.fail();
            }
        }catch(Exception e){
            log.error(e.getMessage());
            test.log(Status.ERROR, e.getMessage());

        }
    }

    public static boolean isFileDownloaded(String fileName) throws InterruptedException {

        click("xpath", SampleFileBtn);
        log.info(" File Downloaded Successfully");
        Thread.sleep(1000);
        File dir = new File(DownloadsfolderPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {

            if (dir_contents[i].getName().equals(fileName)) {

                File f = new File(DownloadsfolderPath + fileName);
                f.delete();
                log.info(DownloadsfolderPath + fileName+" File Deleted Successfully");

                return true;
            }
        }
        return false;
    }


    public static boolean containsSpecialChars(String input, String specialChars) {
        for (char c : input.toCharArray()) {
            if (specialChars.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if a string contains a number
    private static boolean containsNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
