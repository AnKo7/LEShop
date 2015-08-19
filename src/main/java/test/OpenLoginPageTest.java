package main.java.test;

import main.java.pages.LEShopLoginPage;
import main.java.pages.LEShopMainPage;
import main.java.setup.LEShopHelper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static main.java.setup.SeleniumDriver.clearBrowserCache;
import static main.java.setup.SeleniumDriver.getDriver;
import static org.testng.Assert.assertTrue;

public class OpenLoginPageTest {

    public static final String EMAIL = "nastyna@ukr.net";
    public static final String PASSWORD = "zxcasd";
    public static final String INCORRECT_PASSWORD = "010101";
    public static final String LOGIN = "AnKo";


    @Test(groups = {"positive"})
    public void loginWithCorrectCredentials() {
        LEShopLoginPage loginPage = new LEShopMainPage().open(getDriver()).goToLoginPage(getDriver());
        loginPage.login(EMAIL, PASSWORD);
        LEShopHelper.getScreenshot(getDriver());
        assertTrue(loginPage.getWelcomeUser().getText().contains(LOGIN));
    }

    @AfterMethod
    public static void clearCache() {
        clearBrowserCache();
    }

    @Test(groups = {"negative"})
    public void loginWithInCorrectCredentials() {

        LEShopLoginPage loginPage = new LEShopMainPage().open(getDriver()).goToLoginPage(getDriver());
        loginPage.login(EMAIL, INCORRECT_PASSWORD);
        ExpectedConditions.textToBePresentInElement(loginPage.getNoMatchError(), LOGIN.toLowerCase());
        LEShopHelper.getScreenshot(getDriver());
        assertTrue(loginPage.getNoMatchError().getText().contains(EMAIL.toLowerCase().substring(0, 17)));

    }

    @AfterClass
    public static void tearDown() {
        clearBrowserCache();
        getDriver().quit();
        getDriver().close();
    }
}
