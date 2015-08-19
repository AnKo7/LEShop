package main.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static main.java.setup.SeleniumDriver.getDriver;

public abstract class LEShopPage <T> {
    private static final String BASE_URL = "http://leshop.testserv.leservice.info/";
    private static final int LOAD_TIMEOUT = 30;
    private static final int REFRESH_RATE = 2;

    public T openPage(Class<T> clazz, WebDriver driver) {
        T page = PageFactory.initElements(driver, clazz);
        getDriver().get(BASE_URL + getPageUrl());
        ExpectedCondition<?> pageLoadCondition = ((LEShopPage<?>) page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
    }

    private void waitForPageToLoad(ExpectedCondition<?> pageLoadCondition) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
    }

    protected abstract ExpectedCondition getPageLoadCondition();

    public abstract String getPageUrl();


}

