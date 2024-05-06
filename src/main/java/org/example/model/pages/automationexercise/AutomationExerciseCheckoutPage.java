package org.example.model.pages.automationexercise;

import org.example.model.pages.Page;
import org.openqa.selenium.WebDriver;

public class AutomationExerciseCheckoutPage extends Page<AutomationExerciseCheckoutPage> {

    public AutomationExerciseCheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        driver.get("https://www.automationexercise.com/checkout");
    }

    @Override
    public void isLoaded() {
        assert driver.getTitle().contains("Checkout") : "Failed to assert the user is on the checkout page";
    }
}
