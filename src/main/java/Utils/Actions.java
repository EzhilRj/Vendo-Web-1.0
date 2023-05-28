package Utils;

import Base.Setup;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static UIObjects.Lookup_PageObjects.*;
import static Utils.Constants.*;
import static Utils.ExtentReportListener.test;

public class Actions extends Setup {

    public static String drpName = "TAMIL NADU";
    public static String value;
    public static String Addedinput = "Kanchipuram";
    public static String updatedinput = "Testnaduss1";
    public static String searchname = "TAMIL NADU";
    public static WebElement element;

    public static void click(String attributeName, String attributeValue) {
        String AN = attributeName.toUpperCase();
        switch (AN) {
            case "ID" -> driver.findElement(By.id(attributeValue)).click();
            case "NAME" -> driver.findElement(By.name(attributeValue)).click();
            case "XPATH" -> driver.findElement(By.xpath(attributeValue)).click();
            case "CLASSNAME" -> driver.findElement(By.className(attributeValue)).click();
            case "CSSSELECTOR" -> driver.findElement(By.cssSelector(attributeValue)).click();
            case "TAGNAME" -> driver.findElement(By.tagName(attributeValue)).click();
        }
    }


    public static void Enter(String attributeName, String attributeValue, String inputText) {
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


    public static void Wait(String attributeName, String attributeValue, WebDriver driver) {

        String AN = attributeName.toUpperCase();
        switch (AN) {
            case "ID" -> {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(attributeValue)));
            }

            case "XPATH" -> {

                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attributeValue)));

            }
            case "CLASSNAME" -> {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(attributeValue)));
            }
            case "CSSSELECTOR" -> {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attributeValue)));
            }

            default -> System.out.println("Invalid attribute name specified: " + attributeName + attributeValue);
        }

    }

    public static void VerifyActions(String firstrowvalue, String Addedvalue, String fieldname, String UpdatedValue, String Updsuccessmsg, String Delsuccesmsg, String MasterDatas) throws InterruptedException {

        Wait("xpath", firstrowvalue, driver);
        value = gettext("xpath", firstrowvalue);
        if (value.equalsIgnoreCase(Addedvalue)) {
            Thread.sleep(1000);
            log.info("Updating the added State");
            click("xpath", Editbutton);
            Enter("xpath", fieldname, updatedinput);
            click("xpath", SaveButton);
            Thread.sleep(1000);
            try {
                if((Source(stateUpdatedSuccessmsg))){
                    test.log(Status.PASS,"EDIT : Testdata : "+updatedinput+" | Testoutput : "+gettext("xpath",Toastcontent));
                    Assert.assertTrue(true);
                }else {
                    Thread.sleep(1000);
                    test.log(Status.FAIL,"EDIT : Negative Data is Given | Testdata : "+updatedinput+" | Testoutput : "+gettext("xpath",Toastcontent));
                    softAssert.assertTrue(false);
                }
            } catch (Exception e) {
                test.log(Status.DEBUG,e.getMessage());
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
                if((Source(stateDeletesuccessmsg))){
                    test.log(Status.PASS,"DELETE : Testdata : "+updatedinput+" | Testoutput : "+gettext("xpath",Toastcontent));
                    Assert.assertTrue(true);
                }else {
                    Thread.sleep(1000);
                    test.log(Status.FAIL,"DELETE : Negative Data is Given | Testdata :  "+updatedinput+" | Testoutput : "+gettext("xpath",Toastcontent));
                    softAssert.assertTrue(false);
                }
                Wait("xpath", MasterDatas, driver);
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
        }catch (Exception e) {
            test.log(Status.DEBUG,e.getMessage());
            e.printStackTrace();
        }

        softAssert.assertAll();

    }

    public static void Dropdown(By ddoptions, By lists) throws InterruptedException {
        int options = driver.findElements(ddoptions).size();
        for (int i = 1; i < options; i++) {
            String ele = driver.findElement(By.xpath("//*[@data-ng-repeat='state in liststate'][" + i + "]")).getText();
            if (ele.equalsIgnoreCase(drpName)) {
                driver.findElement(By.xpath("//*[@data-ng-repeat='state in liststate'][" + i + "]")).click();
                log.info("Given State is Selected in the dropdown");
                break;
            }
        }
    }

    public static void UploadSampleFile(String excelPath) throws InterruptedException, AWTException {
        Robot rs = new Robot();
        rs.delay(2000);
        StringSelection ss = new StringSelection(excelPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        rs.keyPress(KeyEvent.VK_CONTROL);
        rs.keyPress(KeyEvent.VK_V);
        rs.delay(2000);
        rs.keyRelease(KeyEvent.VK_CONTROL);
        rs.keyRelease(KeyEvent.VK_V);
        log.info("File Location sent !");
        rs.keyPress(KeyEvent.VK_ENTER);
        rs.keyRelease(KeyEvent.VK_ENTER);
        log.info("File uploaded successfully");
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

    public static boolean Source(String value){

        if(driver.getPageSource().contains(value)){
            return true;
        }
        return false;


    }
}
