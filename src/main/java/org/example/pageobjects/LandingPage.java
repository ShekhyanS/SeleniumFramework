package org.example.pageobjects;

import org.example.util.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //PageFactory
    // @FindBy annotation instead of WebElement userEmail = driver.findElement(By.id("userEmail"));
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id ="userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement loginBtn;
    @FindBy(css = "[class *= 'flyInOut']")
    WebElement message;
    public ProductCatalogPage loginApplication(String emailValue, String passValue){
        userEmail.sendKeys(emailValue);
        password.sendKeys(passValue);
        loginBtn.click();
        return new ProductCatalogPage(driver);

    }
    public String getMessageText(){
        waitForElementToAppear(message);
        return message.getText();
    }

    public void   goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }











}