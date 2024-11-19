package tests;

import org.example.pageobjects.MyCartPage;
import org.example.pageobjects.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testcomponents.BaseTest;
import testcomponents.Retry;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {
        String user = "wrong@email.com";
        String pass = "Sonatest@1";
        landingPage.loginApplication(user, pass);
        Assert.assertEquals(landingPage.getMessageText(), "Incorrect email or password!!!.");
    }
    @Test
    public void productErrorValidation() {

        String productName = "ZARA COAT 3";
        String user = "rahulshetty@gmail.com";
        String pass = "Iamking@000";
        ProductCatalogPage productCatalog = landingPage.loginApplication(user, pass);

        productCatalog.addProductToCart(productName);

        MyCartPage myCartPage = productCatalog.openCart();
        Assert.assertFalse(myCartPage.productIsInCart("ZARA COAT 33"));




    }

}
