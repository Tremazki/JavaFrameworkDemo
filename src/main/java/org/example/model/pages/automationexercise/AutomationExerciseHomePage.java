package org.example.model.pages.automationexercise;

import org.example.model.pages.TestStep;
import org.example.model.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
        assert driver.getTitle().contains("Automation Exercise") : "Failed to assert the user is on the home page";
    }

    @TestStep("The user clicks on login")
    public void clickLogin(){
        wait.until(ExpectedConditions.visibilityOf(loginLink));
        loginLink.click();
    }

    @TestStep("The user asserts they're on the signup page")
    public void assertTitle() {
        assert driver.getTitle().contains("Sign up") : "Failed to assert the user is on the sign-up page";
    }

}
