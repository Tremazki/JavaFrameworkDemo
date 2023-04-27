package org.example.pages.automationexercise;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutomationExerciseHomePage extends AbstractPage {

    static final String loginLocator = ".//a[@href=\"/login\"]";

    @FindBy(how = How.XPATH, using = loginLocator)
    WebElement loginLink;

    public AutomationExerciseHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin(){
        wait.until(ExpectedConditions.visibilityOf(loginLink));
        loginLink.click();
    }
}
