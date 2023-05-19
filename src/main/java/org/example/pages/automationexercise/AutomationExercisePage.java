package org.example.pages.automationexercise;

import org.example.pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AutomationExercisePage extends Page<AutomationExercisePage> {

    public AutomationExercisePage(WebDriver driver) {
        super(driver);
    }

    protected abstract void load();

    protected abstract void isLoaded();

    public void resolveGoogleAds() {
        ((JavascriptExecutor) driver).executeScript(
            "const elements = document.getElementsByClassName(\"google-auto-placed\"); " +
            "while (elements.length > 0) elements[0].remove();"
        );

        ((JavascriptExecutor) driver).executeScript(
            "const elements = document.getElementsByClassName(\"adsbygoogle adsbygoogle-noablate\"); " +
            "while (elements.length > 0) elements[0].remove();"
        );
    }
}
