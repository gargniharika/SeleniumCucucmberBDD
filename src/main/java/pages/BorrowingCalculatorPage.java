package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SeleniumDriver;
import utils.SeleniumHelper;

import java.util.*;

public class BorrowingCalculatorPage {
    String applicationTypeXpath = "//input[@type='radio' and @id='<application_type>']";
    String propertyTypeXpath = "//input[@type='radio' and @id='<borrow_type>']";
    String inputFieldsXpath = "//label[text()='<Field Name>']/../div[1]/input";
    @FindBy(xpath = "//select[@title='Number of dependants']")
    private WebElement selectDependants;
    @FindBy(xpath = "//label[text()='Your income (before tax)']/../div[1]/input")
    private WebElement incomeBeforeTax;
    @FindBy(xpath = "//label[text()='Your other income']/../div[1]/input")
    private WebElement otherIncome;
    @FindBy(xpath = "//label[text()='Living expenses']/../div[1]/input")
    private WebElement livingExpenses;
    @FindBy(xpath = "//label[text()='Current home loan repayments']/../div[1]/input")
    private WebElement currentHLRepayments;
    @FindBy(xpath = "//label[text()='Other loan repayments']/../div[1]/input")
    private WebElement otherLoanRepayments;
    @FindBy(xpath = "//label[text()='Other commitments']/../div[1]/input")
    private WebElement otherCommitments;
    @FindBy(xpath = "//label[text()='Total credit card limits']/../div[1]/input")
    private WebElement totalCCLimits;
    @FindBy(xpath = "//button[@id='btnBorrowCalculater']")
    private WebElement calculateButton;
    @FindBy(xpath = "//button[@class='start-over']")
    private WebElement startOverButton;
    @FindBy(xpath = "//span[@class='borrow__error__text']")
    private WebElement borrowingErrorText;
    @FindBy(xpath = "//span[@id='borrowResultTextAmount']")
    private WebElement borrowingAmountText;

    Map<String, WebElement> elementMap = new HashMap<String, WebElement>();

    public void loadPageElements() {
        elementMap.put("Work out how much I could borrow", calculateButton);
        elementMap.put("Borrowing Limit", borrowingAmountText);
        elementMap.put("Message", borrowingErrorText);
        elementMap.put("Start Over", startOverButton);
        elementMap.put("Income",incomeBeforeTax);
        elementMap.put("Other Income",otherIncome);
        elementMap.put("Other Commitments",otherCommitments);
        elementMap.put("Other Loan Repayments",otherLoanRepayments);
        elementMap.put("Current Home Loan Repayments",currentHLRepayments);
        elementMap.put("Total Credit Card Limits",totalCCLimits);
        elementMap.put("Living Expenses",livingExpenses);
    }

    public void navigateToHomePage()
    {
        SeleniumDriver.openPage();
        SeleniumDriver.waitForPageToLoad();
        loadPageElements();
    }

    public void populateInputField(String key, String inputValue) {
        //populate the form data
        switch (key) {
            case "Application Type":
                applicationTypeXpath = applicationTypeXpath.replace("<application_type>", "application_type_" + inputValue.toLowerCase());
                SeleniumHelper.click(SeleniumDriver.getDriver().findElement(By.xpath(applicationTypeXpath)));
                break;
            case "Dependants":
                SeleniumHelper.selectByText(selectDependants, inputValue);
                break;
            case "Property Type":
                propertyTypeXpath = propertyTypeXpath.replace("<borrow_type>", "borrow_type_" + inputValue.toLowerCase());
                SeleniumHelper.click(SeleniumDriver.getDriver().findElement(By.xpath(propertyTypeXpath)));
                break;
            case "Income":
            case "Other Income":
            case "Current Home Loan Repayments":
            case "Living Expenses":
            case "Other Loan Repayments":
            case "Other Commitments":
            case "Total Credit Card Limits":
                SeleniumHelper.enterText(elementMap.get(key) , inputValue);
                break;

            default:System.out.println("Unknown Input field provided");
                break;
        }
    }

    public void clickOnButton(String buttonType) {
        SeleniumHelper.click(elementMap.get(buttonType));
    }

    public String getElementText(String key) throws InterruptedException {
        if(key.equals("Borrowing Limit")) {
            //SeleniumDriver.getWait().until(ExpectedConditions.visibilityOf(borrowingAmountText));
            //user need to wait for 1-2 seconds for calculation to finish
            Thread.sleep(2000);
        }
        return SeleniumHelper.getElementText(elementMap.get(key));
    }

    public String getInputText(String key) {
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getDriver();
        return (String) js.executeScript("return arguments[0].value", elementMap.get(key));
    }
}
