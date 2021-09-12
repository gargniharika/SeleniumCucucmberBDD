package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDriver {
    //initialize webdriver
    private static WebDriver driver;
    public static Properties prop = new Properties();

    //initialize timeouts
    private static WebDriverWait waitDriver;
    public final static String CONFIG_FILE_PATH="src/test/resources/config";
    public final static String CONFIG_FILE_NAME="config.properties";
    public final static int TIMEOUT = 30;
    public final static int PAGE_LOAD_TIMEOUT = 50;
    public static String URL;

    public SeleniumDriver() {
        gatherConfigProperties();
    }

    private void gatherConfigProperties()
    {
        try {
            String userDir= System.getProperty("user.dir");
            prop.load( new FileInputStream(userDir+ File.separator + CONFIG_FILE_PATH+File.separator+CONFIG_FILE_NAME) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL=prop.getProperty("URL");
    }

    public static void openPage() {
        System.out.println("Launching URL :" + URL);
        driver.get(URL);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return waitDriver;
    }

    public static void setUpDriver() {
        String browser = prop.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":

                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                System.out.println("Browser type unknown/not-supported");
        }

        waitDriver = new WebDriverWait(driver, TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        String window = driver.getWindowHandle();
        System.out.println("Window ->" + window);
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public static void waitForPageToLoad() {
        try {
            ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
