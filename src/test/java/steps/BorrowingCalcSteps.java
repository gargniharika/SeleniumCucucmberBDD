package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.LineIterator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BorrowingCalculatorPage;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

import java.util.*;

import static org.testng.Assert.assertTrue;

public class BorrowingCalcSteps extends SeleniumDriver {
    BorrowingCalculatorPage borrowingCalculatorPage = null;

    @Given("user navigates to ANZ Borrowing Calculator Page")
    public void userNavigatesToANZBorrowingCalculatorPage() {
        borrowingCalculatorPage = PageFactory.initElements(SeleniumDriver.getDriver(), BorrowingCalculatorPage.class);
        borrowingCalculatorPage.navigateToHomePage();
    }

    @Given("user is on webPage with Title {string}")
    public void userIsOnWebPageWithTitle(String pageTitle) {
        Assert.assertEquals(SeleniumHelper.getPageTitle(), pageTitle);
    }

    @When("user enters the personal details")
    public void userEntersThePersonalDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> inputData : data) {
            for (Map.Entry<String, String> entry : inputData.entrySet()) {
                borrowingCalculatorPage.populateInputField(entry.getKey(), entry.getValue());
            }
        }
    }

    @Then("user verifies the {string} as {string}")
    public void userVerifiesTheAs(String key, String value) throws InterruptedException {
        Assert.assertEquals(borrowingCalculatorPage.getElementText(key), value);
    }

    @And("user clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        borrowingCalculatorPage.clickOnButton(buttonName);
    }

    @And("user verifies the input field {string} as {string}")
    public void userVerifiesTheInputFieldAs(String key, String value) {
        Assert.assertEquals(borrowingCalculatorPage.getInputText(key).replaceAll(",", ""), value);
    }
}
