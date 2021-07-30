package com.qa.everlight.computerDatabase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAComputerPage extends BasePage {
    public AddAComputerPage(WebDriver driver) {
        super(driver);
    }

    /*
     * Computer name Text Field
     * ***************************************************************
     */
    public WebElement getComputerNameTextFieldElement() {
        return driver.findElement(By.id("name"));
    }

    public void setComputerNameTextField(String value) {
        getComputerNameTextFieldElement().clear();
        getComputerNameTextFieldElement().sendKeys(value);
    }

    /*
     * Introduced Text Field
     * ***************************************************************
     */

    public WebElement getIntroducedTextFieldElement() {
        return driver.findElement(By.id("introduced"));
    }

    public void setIntroducedTextField(String value) {
        getIntroducedTextFieldElement().clear();
        getIntroducedTextFieldElement().sendKeys(value);
    }

    /*
     * Discontinued Text Field
     * ***************************************************************
     */
    public WebElement getDiscontinuedTextFieldElement() {
        return driver.findElement(By.id("discontinued"));
    }

    public void setDiscontinuedTextField(String value) {
        getDiscontinuedTextFieldElement().clear();
        getDiscontinuedTextFieldElement().sendKeys(value);
    }

    /*
     * Company Dropdown
     * ***************************************************************
     */

    public WebElement getCompanyDropdownElement() {
        return driver.findElement(By.id("company"));
    }

    public Select getCompanyDropdownSelect() {
        return new Select(getCompanyDropdownElement());
    }
    public void setCompanyDropdownByText(String text) {
        getCompanyDropdownSelect().selectByVisibleText(text);
    }

    /*
     * Create this computer Button
     * ***************************************************************
     */

    public WebElement getCreateThisComputerButtonElement() {
        return driver.findElement(By.xpath("//input[@value='Create this computer']"));
    }

    public void clickCreateThisComputerButton() {
        getCreateThisComputerButtonElement().click();
    }

    public HomePage clickCreateThisComputerButtonGetHomePage() {
        getCreateThisComputerButtonElement().click();
        return new HomePage(driver);
    }

    /*
     * Failed to refine type : Predicate isEmpty() did not fail Error Message
     * ***************************************************************
     */

    public String getInvalidInputErrorMessage(String text) {
        new WebDriverWait(driver,4).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='name']//parent::div[@class='input']//span")));
        String errorMessage = driver.findElement(By.xpath("//input[@id='" + text + "']//parent::div[@class='input']//span")).getText();
        return errorMessage;
    }

    /*
     * Failed to decode date : java.time.format.DateTimeParseException Error Message
     * ***************************************************************
     *//*

    public String getFailedToDecodeDateErrorMessage() {
        new WebDriverWait(driver,4).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='name']//parent::div[@class='input']//span")));
        String errorMessage = driver.findElement(By.xpath("//input[@id='introduced']//parent::div[@class='input']//span")).getText();
        return errorMessage;
    }*/




}
