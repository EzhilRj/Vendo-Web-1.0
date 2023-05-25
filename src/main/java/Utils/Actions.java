package Utils;

import Base.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static PageObjects.Lookup_PageObjects.*;
import static PageObjects.Lookup_PageObjects.Masterstatedatas;
import static Utils.Constants.*;

public class Actions extends Setup {

    public static String drpName = "TAMIL NADU";
    public static String value;
    public static String statename = "Tamilnaduss";
    static String updatedinput = "Testnaduss1";
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

    public static void VerifyActions(String attributevalue, String Addedvalue, String fieldname, String updatevalue, String updsuccesmsg, String Delsuccesmsg) throws InterruptedException {
        Wait("xpath", TestInput, driver);
        value = gettext("xpath", TestInput);
        if (value.equalsIgnoreCase(statename)) {
            Thread.sleep(1000);
            log.info("Updating the added State");
            click("xpath", Editbutton);
            Enter("xpath", fieldname, updatedinput);
            click("xpath", SaveButton);
            try {
                if (driver.getPageSource().contains(gettext("xpath", UpdatedSuccessmsg))) {
                    Thread.sleep(1000);
                    if (gettext("xpath", TestInput).equals(updatedinput)) {
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
        value = gettext("xpath", TestInput);
        if (value.equalsIgnoreCase(updatedinput)) {
            click("xpath", Deletebutton);
            click("xpath", YesButton);
            try {
                Wait("xpath", Deletesuccessmsg, driver);
                if (driver.getPageSource().contains(gettext("xpath", Deletesuccessmsg))) {
                    Wait("xpath", Masterstatedatas, driver);
                    List<WebElement> states = driver.findElements(By.xpath(Masterstatedatas));
                    boolean deleted = true;
                    log.info("Deleted the added state");
                    for (WebElement element : states) {
                        String value = element.getText();
                        deleted = value.contains(updatedinput);
                    }
                    Assert.assertEquals(deleted, false);
                } else {
                    Assert.assertEquals(true, false);
                }
            } catch (Exception e) {
                Assert.assertTrue(false);
                e.printStackTrace();
            }
        }
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
}
