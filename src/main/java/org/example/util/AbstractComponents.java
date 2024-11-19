package org.example.util;

import org.example.pageobjects.MyCartPage;
import org.example.pageobjects.OrderHystoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;
    //WebDriverWait wait;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cart;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orders;



    public AbstractComponents(WebDriver driver) {
        this.driver = driver;

    }

    public WebDriverWait initWait( int sec) {
        return  new WebDriverWait(driver, Duration.ofSeconds(sec));
    }
    public void waitForElementToAppear(By findBy) {
        initWait(5).until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement webElement){
        initWait(5).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToDisappear(By findBy) {
        initWait(2).until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForElementIsClickable(By findBy){
        initWait(5).until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public void enterValueToField(WebElement webElement, String value){
        Actions action = new Actions(driver);
        action.sendKeys(webElement, value).build().perform();
    }

    public WebElement webElementBuilder(String valueToBeUpdated, String valueToReplaceWith){
        String dynamicXpath = String.format(valueToBeUpdated, valueToReplaceWith);
        return  driver.findElement(By.xpath(dynamicXpath));
    }
    public MyCartPage openCart() {
        cart.click();
        return new MyCartPage(driver);
    }

    public OrderHystoryPage openOrdersHistory(){
        orders.click();
        return new OrderHystoryPage(driver);
    }
}
