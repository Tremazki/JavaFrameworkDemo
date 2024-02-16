package org.example.model.pages.automationexercise;

import org.example.model.pages.TestStep;
import org.example.model.pages.Page;
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

    @TestStep("The user asserts they're on the checkout page")
    public void assertTitle() {
        assert driver.getTitle().contains("Checkout");
    }
}
