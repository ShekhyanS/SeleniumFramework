package org.example.pageobjects;

import org.example.util.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderHystoryPage extends AbstractComponents {

    WebDriver driver;

    public OrderHystoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public Boolean VerifyOrderIsDisplayed(String productname) {
        return productNames.stream().anyMatch(el -> el.getText().contains(productname));
    }

}
