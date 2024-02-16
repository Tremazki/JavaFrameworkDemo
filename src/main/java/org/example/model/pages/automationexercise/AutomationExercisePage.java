package org.example.model.pages.automationexercise;

import org.example.model.pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
