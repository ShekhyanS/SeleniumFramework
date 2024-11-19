package tests;

import org.example.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testcomponents.BaseTest;

import java.io.IOException;
import java.time.Duration;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData")
    public void submitOrder(String user, String pass) {

        ProductCatalogPage productCatalog = landingPage.loginApplication(user, pass);

        productCatalog.addProductToCart(productName);

        MyCartPage myCartPage = productCatalog.openCart();
        Assert.assertTrue(myCartPage.productIsInCart(productName));

        CheckoutPage checkoutPage = myCartPage.checkout();
        checkoutPage.selectCountry("India");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        Assert.assertEquals(confirmationPage.getMessagText(), "THANKYOU FOR THE ORDER.");

    }
//    // @Test(dependsOnMethods = {"submitOrder"})
//    @Test(dataProvider = "getData")
//    public void orderHistoryTest(String user, String pass) {
//        ProductCatalogPage productCatalog = landingPage.loginApplication(user, pass);
//
//        OrderHystoryPage orderHystoryPage = productCatalog.openOrdersHistory();
//        Assert.assertTrue(orderHystoryPage.VerifyOrderIsDisplayed(productName));
//
//    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"sona@email.com", "Sonatest@1"},
                {"rahulshetty@gmail.com", "Iamking@000"}
        };
    }

}

