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
        assertTitle();
    }

    @TestStep("The user asserts they're on the checkout page")
    public void assertTitle() {
        assert driver.getTitle().contains("Checkout") : "Failed to assert the user is on the checkout page";
    }
}
