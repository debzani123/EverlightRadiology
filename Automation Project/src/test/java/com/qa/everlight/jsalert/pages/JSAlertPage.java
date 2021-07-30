package com.qa.everlight.jsalert.pages;

import com.qa.everlight.computerDatabase.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSAlertPage extends BasePage {
    public JSAlertPage(WebDriver driver) {
        super(driver);
    }

    /*
     * Click For JS Alert Button
     * ***************************************************************
     */

    public WebElement getClickForJSAlertButtonElement() {
        return driver.findElement(By.xpath("//ul//li//button[contains(text(),'Click for JS Alert')]"));
    }

    public void clickClickForJSAlertButton() {
        getClickForJSAlertButtonElement().click();
    }

    /*
     * Click For JS Alert Button
     * ***************************************************************
     */

    public WebElement getClickForJSConfirmButtonElement() {
        return driver.findElement(By.xpath("//ul//li//button[contains(text(),'Click for JS Confirm')]"));
    }

    public void clickClickForJSConfirmButton() {
        getClickForJSConfirmButtonElement().click();
    }

    /*
     * Click for JS Prompt Button
     * ***************************************************************
     */

    public WebElement getClickForJSPromptButtonElement() {
        return driver.findElement(By.xpath("//ul//li//button[contains(text(),'Click for JS Prompt')]"));
    }

    public void clickClickForJSPromptButton() {
        getClickForJSPromptButtonElement().click();
    }

    public WebElement getResultTextElement() {
        return driver.findElement(By.xpath("//p[@id='result']"));
    }

    public String getResultText() {
        return getResultTextElement().getText();
    }
}
