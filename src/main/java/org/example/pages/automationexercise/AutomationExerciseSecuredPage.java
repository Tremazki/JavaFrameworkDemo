package org.example.pages.automationexercise;

import org.example.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AutomationExerciseSecuredPage extends Page<AutomationExerciseSecuredPage> {

    private final String username;
    private final String password;

    static final String logoutLocator = ".//a[@href=\"/logout\"]";

    public AutomationExerciseSecuredPage(WebDriver driver, LoadableComponent<?> parent, String username, String password) {
        super(driver, parent);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void load() {
//        parent.get();
//        String originalUrl = driver.getCurrentUrl();

        // Check if logged in already
        new AutomationExerciseSignUpPage(driver).get().login(username, password);
        parent.get();
    }

    @Override
    protected void isLoaded() {
        try {
            driver.findElement(By.xpath(logoutLocator));
        } catch (NoSuchElementException e ) {
            fail("Failed to locate the logout element");
        }
    }
}
