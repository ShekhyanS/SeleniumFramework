package stepDefinitionForCucumberTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageobjects.*;
import org.testng.Assert;
import testcomponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogPage productCatalog;
    public ConfirmationPage confirmationPage;

    //So you take some row from the test scenario i the feature file and map it to a method, and in the method you call existing code
    //and base words from scenario make as
    @Given("I landed on Ecommerce page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage =
                launchApplication();
    }

    // when you have dynamic variables then you replace it with (.+) regular expression and also enclose the string sentence
    // in ^$ symbols to make it clear that is regex
    @Given("^Logged in with username (.+) and (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
        productCatalog = landingPage.loginApplication(username, password);
    }

    @When("^When I add product (.+) to Cart$")
    public void when_I_add_product_to_cart(String productName) {
        productCatalog.addProductToCart(productName);
    }
    // in our feature file this statement starts with And, actually there is a tag @And, you can use it but it will repeat underneath the tag
    // in previous step, so @when tag. You can here use either @When, or @And
    @When("^checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName){
        MyCartPage myCartPage = productCatalog.openCart();
        Assert.assertTrue(myCartPage.productIsInCart(productName));

        CheckoutPage checkoutPage = myCartPage.checkout();
        checkoutPage.selectCountry("India");

        confirmationPage = checkoutPage.submitOrder();
    }

    @Then ("{string} message is displayed")
    public void  message_is_displayed(String string){
        Assert.assertEquals(landingPage.getMessageText(), string);
    }


    // when there is a dynamic variable which is taken from Examples from feature file, then it is pure regex, but when it is a text,
    // then we just put a curly braces and it will take from step
    @Then("{string} message is displayed on Confirmation page")
    public void mesasge_displayed_confirmatin_page(String string){
        Assert.assertEquals(confirmationPage.getMessagText(), string);
    }
}


