package org.example.pageobjects;

import org.example.util.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder = 'Select Country']")
    WebElement countryField;
    @FindBy(css = ".action__submit")
    WebElement submitBtn;

    String countryOption = "//*[contains(@class, 'ta-results')]//span[text()=' %s']";

    By matchedValue = By.cssSelector("button.ng-star-inserted");

    public void enterCountry(String country) {
        enterValueToField(countryField, country);
    }

    public void selectCountry(String country) {
        enterCountry(country);
        waitForElementToAppear(matchedValue);
        webElementBuilder(countryOption, country).click();
    }

    public ConfirmationPage submitOrder() {
        submitBtn.click();
        return new ConfirmationPage(driver);
    }



}
