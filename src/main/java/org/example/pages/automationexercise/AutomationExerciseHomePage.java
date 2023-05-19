package org.example.pages.automationexercise;

import org.example.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutomationExerciseHomePage extends Page<AutomationExerciseHomePage> {

    static final String loginLocator = ".//a[@href=\"/login\"]";

    @FindBy(how = How.XPATH, using = loginLocator)
    WebElement loginLink;

    public AutomationExerciseHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.automationexercise.com/");
    }

    @Override
    protected void isLoaded() {
        assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    public void clickLogin(){
        wait.until(ExpectedConditions.visibilityOf(loginLink));
        loginLink.click();
    }
}
