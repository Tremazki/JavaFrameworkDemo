package org.example.model.pages.automationexercise;

import org.example.reporting.TestStep;
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
        assert driver.getTitle().contains("Checkout") : "Failed to assert the user is on the checkout page";
    }

    @TestStep("The user asserts they're on the checkout page")
    public void assertTitle() {
        isLoaded();
    }
}
