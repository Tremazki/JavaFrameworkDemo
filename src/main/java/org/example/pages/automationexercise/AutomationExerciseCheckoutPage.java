package org.example.pages.automationexercise;

import org.example.pages.Page;
import org.openqa.selenium.WebDriver;

public class AutomationExerciseCheckoutPage extends Page<AutomationExerciseCheckoutPage> {

    public AutomationExerciseCheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get("https://www.automationexercise.com/checkout");
    }

    @Override
    protected void isLoaded() {
        assert driver.getTitle().contains("Checkout");
    }
}
