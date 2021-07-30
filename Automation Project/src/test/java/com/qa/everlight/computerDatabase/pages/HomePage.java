package com.qa.everlight.computerDatabase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /*
     * Search Text Field
     * ***************************************************************
     */

    public WebElement getSearchTextFieldElement() {
        return driver.findElement(By.id("searchbox"));
    }

    public void setSearchTextField(String value) {
        getSearchTextFieldElement().clear();
        getSearchTextFieldElement().sendKeys(value);
    }

    /*
     * Filter by name Button
     * ***************************************************************
     */

    public WebElement getFilterByNameButtonElement() {
        return driver.findElement(By.id("searchsubmit"));
    }

    public void clickFilterByNameButton() {
        getFilterByNameButtonElement().click();
    }

    /*
     * Add a new computer Button
     * ***************************************************************
     */

    public WebElement getAddANewComputerButtonElement() {
        return driver.findElement(By.id("add"));
    }

    public AddAComputerPage clickAddANewComputerButton() {
        getAddANewComputerButtonElement().click();
        return new AddAComputerPage(driver);
    }

    public WebElement getComputerNameForSpecificRow(int i) {
        return driver.findElement(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[" + i + "]//td[1]"));
    }

    public List<WebElement> getListOfRowFromTable() {
        List<WebElement> listOfRow = driver.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr"));
        return listOfRow;
    }

    public WebElement getColumnValueForSpecificRow(int colNum, int rowNum) {
        WebElement cellValue =  driver.findElement(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[" + rowNum + "]//td[" + colNum + "]"));
        return cellValue;
    }

    /*
     * Successful Warning Message
     * ***************************************************************
     */
    public String getSuccessfulWarningMessage() {
        String warningMessage= driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText();
        return warningMessage;
    }

    /*
     * Found Message with Count
     * ***************************************************************
     */
    public String getFoundMessageWithCountElement() {
        String warningMessage = driver.findElement(By.xpath("//section[@id='main']//h1")).getText();
        return warningMessage;
    }

    /*
     * Computer Name Link
     * ***************************************************************
     */

    public WebElement getComputerNameLinkElement(String computerName) {
        return driver.findElement(By.linkText(computerName));
    }

    public EditComputerPage clickComputerNameLinkElement(String computerName) {
        getComputerNameLinkElement(computerName).click();
        return new EditComputerPage(driver);
    }

    public int getLinkCountForSpecificText(String linkText) {
        List<WebElement> linkTextTotal = driver.findElements(By.linkText(linkText));
        int totalLinkCount = linkTextTotal.size();
        return totalLinkCount;
    }
}
