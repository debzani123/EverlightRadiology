package com.qa.everlight.tests.jsalert;

import com.qa.everlight.jsalert.pages.JSAlertPage;
import com.qa.everlight.tests.BaseTestSuite;
import org.jsoup.Connection;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSAlertTestSuite extends BaseTestSuite {

    @BeforeMethod
    public void setUp() throws Exception {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void checkClickForJSAlertButton() {
        JSAlertPage alertPage = new JSAlertPage(driver);
        alertPage.clickClickForJSAlertButton();
        Alert alert = driver.switchTo().alert();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.equals("I am a JS Alert"), "JS Alert Text is invalid");
        driver.switchTo().alert().accept();
        String actualResultText = alertPage.getResultText();
        Assert.assertEquals(actualResultText, "You successfully clicked an alert", "Result text for Click For JS Alert Button is invalid");
    }

    @Test
    public void checkClickForJSConfirmButton() {
        JSAlertPage alertPage = new JSAlertPage(driver);
        alertPage.clickClickForJSConfirmButton();
        Alert alert = driver.switchTo().alert();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.equals("I am a JS Confirm"), "JS Confirm Text is invalid");
        driver.switchTo().alert().accept();
        String actualResultText = alertPage.getResultText();
        Assert.assertEquals(actualResultText, "You clicked: Ok", "Result text for Click For JS Confirm Button is invalid");
    }

    @Test
    public void checkClickForJSPromptButton() {
        JSAlertPage alertPage = new JSAlertPage(driver);
        alertPage.clickClickForJSPromptButton();
        Alert alert = driver.switchTo().alert();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.equals("I am a JS prompt"), "JS Prompt Text is invalid");
        String inputText = "Test JS value";
        driver.switchTo().alert().sendKeys(inputText);
        driver.switchTo().alert().accept();
        String actualResultText = alertPage.getResultText();
        Assert.assertEquals(actualResultText, "You entered: " + inputText, "Result text for Click For JS Prompt Button is invalid");
    }

    @Test
    public void giveEmptyValueForJSPromptButton() {
        JSAlertPage alertPage = new JSAlertPage(driver);
        alertPage.clickClickForJSPromptButton();
        Alert alert = driver.switchTo().alert();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.equals("I am a JS prompt"), "JS Prompt Text is invalid");
        String inputText = "";
        driver.switchTo().alert().sendKeys(inputText);
        driver.switchTo().alert().accept();
        String actualResultText = alertPage.getResultText();
        Assert.assertEquals(actualResultText, "You entered:" + inputText, "Result text for Click For JS Prompt Button is invalid");
    }

    @Test
    public void dismissJSConfirmAlert() {
        JSAlertPage alertPage = new JSAlertPage(driver);
        alertPage.clickClickForJSConfirmButton();
        Alert alert = driver.switchTo().alert();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.equals("I am a JS Confirm"), "JS Confirm Text is invalid");
        driver.switchTo().alert().dismiss();
        String actualResultText = alertPage.getResultText();
        Assert.assertEquals(actualResultText, "You clicked: Cancel", "Result text for Click For JS Confirm Button is invalid");
    }

}
