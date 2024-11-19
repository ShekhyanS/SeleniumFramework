package testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static org.example.util.ExtentReporterNG.getReportObject;

public class Listenners extends BaseTest implements ITestListener {
    //First need to create an object of extend reports
    ExtentTest test;
    ExtentReports extent = getReportObject();
    // so when tests run in parallel then when test is created in onTestStart() it will overwritten each time
    // so with this thread of ExtentTest type issue will be resolved as each test will be run in a separate thread
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

    public void onTestStart(ITestResult result) {
         test = extent.createTest(result.getMethod().getMethodName());
         extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        //test.log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());


        } catch (Exception e1) {
            e1.printStackTrace();
        }
            String filePath = null;
            try {
                filePath = getScreenshot(result.getMethod().getMethodName(), driver);

            } catch (IOException e) {
                e.printStackTrace();
            }
            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        }


    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
