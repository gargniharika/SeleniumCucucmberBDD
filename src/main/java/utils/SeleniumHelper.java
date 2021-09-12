package utils;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SeleniumHelper {
	

    public static boolean isElementPresent(WebElement webElement) {
        try {
            boolean isPresent = webElement.isDisplayed();
            return isPresent;
        } catch (NoSuchElementException e) {
            return false;
        }
        
    }

    public static void enterText(WebElement webElement, String inputValue) {
        try {
            webElement.click();
            webElement.sendKeys(inputValue);
        } catch (Exception e) {

        }
    }

    public static void selectByText(WebElement webElement, String inputValue) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(inputValue);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPageTitle() {
        return SeleniumDriver.getDriver().getTitle();
    }

    public static String getElementText(WebElement element) {
        try {
            return element.getText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void click(WebElement element) {
        try {
            element.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
