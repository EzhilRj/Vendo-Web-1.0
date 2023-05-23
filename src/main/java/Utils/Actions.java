package Utils;

import Base.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static PageObjects.Lookup_PageObjects.*;
import static PageObjects.Lookup_PageObjects.Masterstatedatas;
import static Utils.Constants.screenshotPath;

public class Actions extends Setup {


    public static String value;
    public static String statename = "Tamilnaduss";
    static String statenameupdated = "Testnaduss1";

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

    public static void Enter(String attributeName, String attributeValue, String inputText){
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

    public static String gettext(String attributeName, String attributeValue){

        String AN = attributeName.toUpperCase();
        String text = null;
        switch (AN) {
            case "ID":
                text = driver.findElement(By.id(attributeValue)).getText();
                break;
            case "NAME" :
                text = driver.findElement(By.name(attributeValue)).getText();
                break;
            case "XPATH":
                text = driver.findElement(By.xpath(attributeValue)).getText();
                break;
            case "CLASSNAME":
                text =  driver.findElement(By.className(attributeValue)).getText();
                break;
            case "CSSSELECTOR":
                text =   driver.findElement(By.cssSelector(attributeValue)).getText();
                break;
            case "TAGNAME" :
                text =  driver.findElement(By.tagName(attributeValue)).getText();
        }

        return text;
    }

    public static void VerifyActions() throws InterruptedException {

        Wait("xpath", Teststate,driver);
        value = gettext("xpath", Teststate);
        if (value.equalsIgnoreCase(statename)) {
            Thread.sleep(1000);
            click("xpath", Editbutton);
            Enter("xpath", statefield, statenameupdated);
            click("xpath", SaveButton);
            try {
                if (driver.getPageSource().contains(gettext("xpath", UpdatedSuccessmsg))) {
                    Thread.sleep(1000);
                    if (gettext("xpath", Teststate).equals(statenameupdated)) {
                        Assert.assertTrue(true, "State updated Succesfully");
                    } else {
                        Assert.assertTrue(false, "State is not updated");
                    }
                }
            } catch (Exception e) {
                Assert.assertTrue(false, "State is not updated");
                e.printStackTrace();
            }
        }
        value = gettext("xpath", Teststate);
        if (value.equalsIgnoreCase(statenameupdated)) {
            click("xpath", Deletebutton);
            click("xpath", YesButton);

            try {
                Wait("xpath", Deletesuccessmsg,driver);
                if (driver.getPageSource().contains(gettext("xpath", Deletesuccessmsg))) {
                    Wait("xpath", Masterstatedatas,driver);
                    List<WebElement> states = driver.findElements(By.xpath(Masterstatedatas));
                    boolean deleted = true;
                    for (WebElement element : states) {
                        String value = element.getText();
                        deleted = value.contains(statenameupdated);
                    }
                    Assert.assertEquals(deleted, false,"State Deleted Succesfully");
                } else {
                    Assert.assertEquals(true, false,"State Not deleted");
                }
            }catch(Exception e){
                Assert.assertTrue(false, "State is not Deleted");
                e.printStackTrace();
            }
        }

    }


    //This Method is Contains Update,delete Functionalities
    public static void VerifyEdit(String attributevalue , String Addedvalue,String field, String updatevalue,String updsuccesmsg ) throws InterruptedException {

        Wait("xpath", attributevalue,driver);
        value = gettext("xpath", attributevalue);
        if (value.equalsIgnoreCase(Addedvalue)) {
            Thread.sleep(1000);
            click("xpath", Editbutton);
            Enter("xpath", field, updatevalue);
            click("xpath", SaveButton);
            try {
                if (driver.getPageSource().contains(gettext("xpath", updsuccesmsg))) {
                    Thread.sleep(1000);
                    if (gettext("xpath", attributevalue).equals(updatevalue)) {
                        Assert.assertTrue(true);
                    } else {
                        Assert.assertTrue(false);
                    }
                }
            } catch (Exception e) {
                Assert.assertTrue(false);
                e.printStackTrace();
            }
        }

    }

    public static void VerifyDelete(String attributevalue , String Addedvalue,String Delsuccesmsg ){

        value = gettext("xpath", attributevalue);
        if (value.equalsIgnoreCase(Addedvalue)) {
            click("xpath", Deletebutton);
            click("xpath", YesButton);

            try {
                Wait("xpath", Delsuccesmsg,driver);
                if (driver.getPageSource().contains(gettext("xpath", Delsuccesmsg))) {

                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }catch(Exception e){
                Assert.assertTrue(false);
                e.printStackTrace();
            }
        }

    }

    public static void Wait(String attributeName, String attributeValue, WebDriver driver){

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


    @AfterMethod
    public void captureScreen(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target = new File(screenshotPath + result.getName() + ".png");

            FileUtils.copyFile(source, target);
        }

    }
}
