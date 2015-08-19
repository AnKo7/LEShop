package main.java.testcases;

import main.java.pages.LEShopLoginPage;
import main.java.pages.LEShopMainPage;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static main.java.setup.SeleniumDriver.clearBrowserCache;
import static main.java.setup.SeleniumDriver.getDriver;
import static org.testng.Assert.assertTrue;

public class OpenLoginPageTest {

    public static final String EMAIL = "nastyna@ukr.net";
    public static final String PASSWORD = "zxcasd";
    public static final String INCORRECT_PASSWORD = "010101";
    public static final String LOGIN = "AnKo";


    WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUpNode(String browser) throws MalformedURLException {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "OSX\\Users\\chumak\\Downloads\\chromedriver");
                driver = new ChromeDriver();
                driver.get("http://leshop.testserv.leservice.info/");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    driver.quit();
                }

            case "firefox":
                /*DesiredCapabilities capability = DesiredCapabilities.firefox();
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                capability.setBrowserName("firefox");
                capability.setVersion("38.0.1");*/

                driver = new FirefoxDriver();

            default:
                break;
        }

    }


    @Test(groups = {"positive"}, priority = 1)
    public void loginWithCorrectCredentials() {
        LEShopLoginPage loginPage = new LEShopMainPage().open(driver).goToLoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);
//        LucianaHelper.getScreenshot(getDriver());
        assertTrue(loginPage.getWelcomeUser().getText().contains(LOGIN));
    }

    @AfterMethod
    public static void clearCache() {
        clearBrowserCache();
    }

    @Test(groups = {"negative"}, priority = 2)
    public void loginWithInCorrectCredentials() {

        LEShopLoginPage loginPage = new LEShopMainPage().open(driver).goToLoginPage(driver);
        loginPage.login(EMAIL, INCORRECT_PASSWORD);
        ExpectedConditions.textToBePresentInElement(loginPage.getNoMatchError(), LOGIN.toLowerCase());
//        LucianaHelper.getScreenshot(getDriver());
        assertTrue(loginPage.getNoMatchError().getText().contains(EMAIL.toLowerCase()));

    }

    @AfterClass
    public static void tearDown() {
        clearBrowserCache();
        getDriver().quit();
        getDriver().close();
    }
}
