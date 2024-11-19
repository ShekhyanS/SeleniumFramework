package testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pageobjects.LandingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initDriver() throws IOException, InterruptedIOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//example//resources//GlobalData.properties");
        prop.load(fileInputStream);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        switch (browserName) {
            case "chrome":

                WebDriverManager.chromedriver().setup(); // this row will check if correct version of chromedriver is installed
                driver = new ChromeDriver();

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // this row will check if correct version of chromedriver is installed
                driver = new FirefoxDriver();

                break;
            case "headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                WebDriverManager.chromedriver().setup(); // this row will check if correct version of chromedriver is installed
                driver = new ChromeDriver(options);
                break;



        }
        if(browserName!="headless"){
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // setting up implicit
        return driver;
    }

    public LandingPage launchApplication() throws IOException {
        driver = initDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        System.out.println(System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png");
        return System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png";
    }


    @BeforeMethod(alwaysRun = true)
    public void setUpTestEnvironment() throws IOException {

        launchApplication();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
