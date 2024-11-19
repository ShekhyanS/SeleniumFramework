package org.example.pageobjects;


import org.example.util.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractComponents {
    WebDriver driver;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class = 'card-body']")
    List<WebElement> products;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cart;

    By productsBy = By.xpath("//*[@class = 'card-body']");
    By addToCart = By.xpath("//button[text() = ' Add To Cart']");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.id("ng-animating");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(s -> s.findElement(By.tagName("b")).getText().contains(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }



}
