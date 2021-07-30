package com.qa.everlight.tests.computerdatabase;

import com.qa.everlight.computerDatabase.pages.AddAComputerPage;
import com.qa.everlight.computerDatabase.pages.EditComputerPage;
import com.qa.everlight.computerDatabase.pages.HomePage;
import com.qa.everlight.tests.BaseTestSuite;
import com.thoughtworks.qdox.model.expression.Add;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class ComputerDatabaseTestSuite extends BaseTestSuite {

    @BeforeMethod
    public void setUp() throws Exception {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://computer-database.gatling.io/computers");
    }

    @Test
    public void getDataForValidComputerName() {
        HomePage homePage = new HomePage(driver);
        String searchText = "ASCI Purple";
        searchText = searchText.toLowerCase();
        homePage.setSearchTextField(searchText);
        homePage.clickFilterByNameButton();
        String actualFoundMessage = homePage.getFoundMessageWithCountElement();
        System.out.println(actualFoundMessage);
        int actualRowCount = homePage.getListOfRowFromTable().size();
        int count;
        boolean found = true;
        if(actualFoundMessage.contains("No computer")) {
            Assert.assertEquals(actualRowCount, 0, "Row Count is not valid" );
        } else if (actualFoundMessage.contains("One")) {
            count = 1;
            String computerName = homePage.getComputerNameForSpecificRow(count).getText();
            computerName = computerName.toLowerCase();
            if (!(computerName.contains(searchText))) {
                found = false;
            }

            if (!found) {
                Assert.fail("Search Table returned invalid value for computer name");
            }
            for (int i = 0; i<4; i++) {
                System.out.println(homePage.getColumnValueForSpecificRow(i+1,1).getText());
            }
        } else {
            String[] splittedFoundMessage = actualFoundMessage.split(" ");
            count = Integer.parseInt(splittedFoundMessage[0]);
            String expectedFoundMessage = count + " " + "computers found";
            Assert.assertEquals(actualFoundMessage, expectedFoundMessage, "Found Message is invalid");
            int expectedRowCount = count;
            Assert.assertEquals(actualRowCount, expectedRowCount, "Search Table has invalid row count");

            for (int i = 0; i < count; i++) {
                String computerName = homePage.getComputerNameForSpecificRow(i + 1).getText();
                computerName = computerName.toLowerCase();
                if (!(computerName.contains(searchText))) {
                    found = false;
                }
            }
            if (!found) {
                Assert.fail("Search Table returned invalid value for computer name");
            }

            for (int rowNum = 0; rowNum<count; rowNum++) {
                for(int colNum = 0; colNum<4; colNum++) {
                    System.out.println(homePage.getColumnValueForSpecificRow(colNum+1, rowNum+1).getText());
                }
            }
        }
    }

    @Test
    public void createANewComputer() {
        HomePage homePage = new HomePage(driver);
        AddAComputerPage addAComputerPage = homePage.clickAddANewComputerButton();
        String computerName = "Debzani Test Computer1";
        addAComputerPage.setComputerNameTextField(computerName);
        addAComputerPage.setIntroducedTextField("1999-08-08");
        addAComputerPage.setDiscontinuedTextField("2009-07-08");
        addAComputerPage.setCompanyDropdownByText("Apple Inc.");
        homePage = addAComputerPage.clickCreateThisComputerButtonGetHomePage();
        String actualMessage = homePage.getSuccessfulWarningMessage();
        String expectedMessage = "Done ! Computer " + computerName + " has been created";
        System.out.println("Actual Message: " + actualMessage);
        System.out.println("Expected Message: " + expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Valid Creation Message is not found");
    }

    @Test
    public void updateComputerInformation() {
        HomePage homePage = new HomePage(driver);
        String searchText = "AN/FSQ-32";
        String updatedText = "AN/FSQ-32_Updated_Deb";
        homePage.setSearchTextField(searchText);
        homePage.clickFilterByNameButton();
        String actualFoundMessage = homePage.getFoundMessageWithCountElement();
        int totalLinkCount = homePage.getLinkCountForSpecificText(searchText);
        if(totalLinkCount>0) {
            EditComputerPage editComputerPage =  homePage.clickComputerNameLinkElement(searchText);
            editComputerPage.setComputerNameTextField(updatedText);
            homePage = editComputerPage.clickSaveThisComputerButtonGetHomePage();
            String actualMessage = homePage.getSuccessfulWarningMessage();
            String expectedMessage = "Done ! Computer " + updatedText + " has been updated";
            Assert.assertEquals(actualMessage, expectedMessage, "Update Successful Message is not found");
        } else {
            System.out.println("No Valid Entry found for given Computer");
        }
    }

    @Test
    public void deleteComputerInformation() {
        HomePage homePage = new HomePage(driver);
        String searchText = "ASCI Purple";
        homePage.setSearchTextField(searchText);
        homePage.clickFilterByNameButton();
        String actualFoundMessage = homePage.getFoundMessageWithCountElement();
        int totalLinkCount = homePage.getLinkCountForSpecificText(searchText);
        if(totalLinkCount>0) {
            EditComputerPage editComputerPage =  homePage.clickComputerNameLinkElement(searchText);
            homePage = editComputerPage.clickDeleteThisComputerButton();
            String actualMessage = homePage.getSuccessfulWarningMessage();
            String expectedMessage = "Done ! Computer " + searchText + " has been deleted";
            Assert.assertEquals(actualMessage, expectedMessage, "Delete Successful Message is not found");
        } else {
            System.out.println("No Valid Entry found for given Computer");
        }
    }

    @Test
    public void createAComputerWithInvalidComputerName() {
        HomePage homePage = new HomePage(driver);
        AddAComputerPage addAComputerPage = homePage.clickAddANewComputerButton();
        String computerName = "";
        addAComputerPage.setComputerNameTextField(computerName);
        addAComputerPage.clickCreateThisComputerButton();
        String actualMessage = addAComputerPage.getInvalidInputErrorMessage("name");
        Assert.assertTrue(actualMessage.contains("Failed to refine type : Predicate isEmpty() did not fail"), "Failed to refine Type Error message is not found");
    }

    @Test
    public void createAComputerWithValidAndInvalidValue() {
        HomePage homePage = new HomePage(driver);
        AddAComputerPage addAComputerPage = homePage.clickAddANewComputerButton();
        String computerName = "Testing Deb";
        addAComputerPage.setComputerNameTextField(computerName);
        addAComputerPage.setIntroducedTextField("1999/08/08");
        addAComputerPage.setCompanyDropdownByText("IBM");
        addAComputerPage.clickCreateThisComputerButton();
        String actualMessage = addAComputerPage.getInvalidInputErrorMessage("introduced");
        Assert.assertTrue(actualMessage.contains("Failed to decode date : java.time.format.DateTimeParseException"), "DateTimeParseExceptionMessage Error message is not found");
    }
}
