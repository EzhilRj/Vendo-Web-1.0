package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static Base.Setup.driver;
import static Base.Setup.testSuiteName;

public  class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    public static ExtentTest test;
    private ExtentHtmlReporter htmlReporter;

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportName = "Vendo Automation Report" + timestamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/main/resources/Reports/" + reportName);
        htmlReporter.config().setDocumentTitle(testSuiteName+" Report");
        htmlReporter.config().setReportName("Vendo Web Automation");
        htmlReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Method " + result.getMethod().getMethodName() + " Started...");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
        test.log(Status.INFO, "Test Method " + result.getMethod().getMethodName() + " Passed...");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
        test.log(Status.FAIL, "Test Method " + result.getMethod().getMethodName() + " Failed...");
        test.log(Status.FAIL, result.getThrowable());
        try{
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            String base64Screenshot = screenshot.getScreenshotAs(OutputType.BASE64);
            test.fail("Screenshot is Attached here :  ", MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64," + base64Screenshot).build());
        } catch (Exception e) {
            test.fail("Failed to capture screenshot: " + e.getMessage());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
        test.log(Status.SKIP, "Test Method " + result.getMethod().getMethodName() + " Skipped...");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @AfterTest
    public void tearDown()
    {
        extent.flush();
    }

}


