package main.java.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LEShopLoginPage extends LEShopPage<LEShopLoginPage> {

    @FindBy(id = "input-email")
    WebElement emailField;

    @FindBy(id = "input-password")
    WebElement passwordField;

    @FindBy(xpath = "/button[contains(., 'Login')]")
    WebElement loginButton;

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement welcomeMyAccount;

    @FindBy(className = ".alert.alert-danger")
    WebElement noMatchError;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(emailField);
    }

    @Override
    public String getPageUrl() {
        return "index.php?route=account/login";
    }

    public void login(String email, String password) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

    }

    public WebElement getWelcomeUser() {
        return welcomeMyAccount;
    }

    public WebElement getNoMatchError() {
        return noMatchError;
    }
}
