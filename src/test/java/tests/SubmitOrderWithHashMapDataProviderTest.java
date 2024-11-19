package tests;

import org.example.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testcomponents.BaseTest;

import java.util.HashMap;

public class SubmitOrderWithHashMapDataProviderTest extends BaseTest {

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

    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> hashMap) {
        ProductCatalogPage productCatalog = landingPage.loginApplication(hashMap.get("email"), hashMap.get("pass"));
        OrderHystoryPage orderHystoryPage = productCatalog.openOrdersHistory();
        Assert.assertTrue(orderHystoryPage.VerifyOrderIsDisplayed(productName));

    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> hashMap1 =  new HashMap<>();
        hashMap1.put("email", "sona@email.com");
        hashMap1.put("pass", "Sonatest@1");

        HashMap<String, String> hashMap2 =  new HashMap<>();
        hashMap2.put("email", "rahulshetty@gmail.com");
        hashMap2.put("pass", "Iamking@000");

        return new Object[][]{{hashMap1},{hashMap2}
        };
    }

}

