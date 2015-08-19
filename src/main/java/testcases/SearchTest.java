package main.java.testcases;

import main.java.pages.LEShopMainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static main.java.setup.SeleniumDriver.clearBrowserCache;
import static main.java.setup.SeleniumDriver.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest {

    public static final String SEARCHGUERY= "Activia";

    @Test(groups = {"positive"})
    public void searchTest() {
        LEShopMainPage page = new LEShopMainPage().open(getDriver());
        page.mainPageSearch(SEARCHGUERY);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(page.getTitle().getText().contains("Search - Activia"));
        assertEquals(page.getProducts().size(), 2);
    }

    @AfterClass
    public static void tearDown() {
        clearBrowserCache();
        getDriver().quit();
        getDriver().close();

    }
}
