package main.java.pages;

import com.sun.tools.javac.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LEShopMainPage extends LEShopPage<LEShopMainPage> {

    @FindBy(id="search-inner")
    WebElement searchField;
    @FindBy(css=".button-search")
    WebElement searchButton;
    @FindBy(css = ".pagination__result")
    WebElement title;
    @FindBy(xpath = ".//*[@class='product-wrapper']")
    List<WebElement> products;


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.titleContains("Products");
    }

    @Override
    public String getPageUrl() {
        return "";
    }

    public LEShopLoginPage goToLoginPage(WebDriver driver) {
        return new LEShopLoginPage().openPage(LEShopLoginPage.class, driver);
    }

    public LEShopMainPage open(WebDriver driver) {
        return new LEShopMainPage().openPage(LEShopMainPage.class, driver);
    }

    public void mainPageSearch(String text){
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.RETURN);
    }

    public WebElement getTitle() {
        return title;
    }

    public List<WebElement> getProducts() {
        return products;
    }
}
