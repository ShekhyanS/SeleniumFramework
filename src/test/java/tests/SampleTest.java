package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class SampleTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // this row will check if correct version of chromedriver is installed
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // setting up implicit wait
        driver.get("https://rahulshettyacademy.com/client");


        String user = "sona@email.com";
        String pass = "Sonatest@1";
        String productName = "ZARA COAT 3";

        WebElement userNameField = driver.findElement(By.id("userEmail"));
        userNameField.clear();
        userNameField.sendKeys(user);

        WebElement passField = driver.findElement(By.id("userPassword"));
        passField.clear();
        passField.sendKeys(pass);

        WebElement loginBtn = driver.findElement(By.id("login"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class = 'card-body']")));

        List<WebElement> products = driver.findElements(By.xpath("//*[@class = 'card-body']"));
        WebElement product = products.stream().filter(s -> s.findElement(By.tagName("b")).getText().contains(productName))
                .findFirst().orElse(null);
        product.findElement(By.xpath("//button[text() = ' Add To Cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ng-animating")));


        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Assert.assertTrue(cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName)));

        driver.findElement(By.cssSelector(".subtotal button")).click();

        WebElement countryField = driver.findElement(By.cssSelector("[placeholder = 'Select Country']"));

        Actions action = new Actions(driver);
        action.sendKeys(countryField, "India").build().perform();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button.ng-star-inserted")));
        driver.findElement(By.xpath("//*[contains(@class, 'ta-results')]//span[text()=' India']")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(),"THANKYOU FOR THE ORDER.");

       // driver.close();
    }

}
