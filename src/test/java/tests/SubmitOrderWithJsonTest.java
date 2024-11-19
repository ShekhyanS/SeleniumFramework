package tests;

import org.example.util.DataReader;
import org.example.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderWithJsonTest extends BaseTest {
    //    String user = "sona@email.com";
//    String pass = "Sonatest@1";
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> hashMap) {

        ProductCatalogPage productCatalog = landingPage.loginApplication(hashMap.get("email"), hashMap.get("pass"));

        productCatalog.addProductToCart(productName);

        MyCartPage myCartPage = productCatalog.openCart();
        Assert.assertTrue(myCartPage.productIsInCart(productName));

        CheckoutPage checkoutPage = myCartPage.checkout();
        checkoutPage.selectCountry("India");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        Assert.assertEquals(confirmationPage.getMessagText(), "THANKYOU FOR THE ORDER.");

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(String user, String pass) {
        ProductCatalogPage productCatalog = landingPage.loginApplication(user, pass);
        OrderHystoryPage orderHystoryPage = productCatalog.openOrdersHistory();
        Assert.assertTrue(orderHystoryPage.VerifyOrderIsDisplayed(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = DataReader.getJsonDataToMap("//src//test//java//data//purchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}
        };
    }

}

