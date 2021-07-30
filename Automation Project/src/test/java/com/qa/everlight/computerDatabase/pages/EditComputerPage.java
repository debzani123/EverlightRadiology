package com.qa.everlight.computerDatabase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditComputerPage extends BasePage {
    public EditComputerPage(WebDriver driver) {
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
     * Save this computer Button
     * ***************************************************************
     */

    public WebElement getSaveThisComputerButtonElement() {
        return driver.findElement(By.xpath("//input[@value='Save this computer']"));
    }

    public void clickSaveThisComputerButton() {
        getSaveThisComputerButtonElement().click();
    }

    public HomePage clickSaveThisComputerButtonGetHomePage() {
        getSaveThisComputerButtonElement().click();
        return new HomePage(driver);
    }

    /*
     * Delete this computer Button
     * ***************************************************************
     */

    public WebElement getDeleteThisComputerButtonElement() {
        return driver.findElement(By.xpath("//input[@value='Delete this computer']"));
    }

    public HomePage clickDeleteThisComputerButton() {
        getDeleteThisComputerButtonElement().click();
        return new HomePage(driver);
    }
}
