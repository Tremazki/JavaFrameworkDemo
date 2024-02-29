package org.example.selenium.automationexercise;

import org.example.junit.SeleniumTest;
import org.example.model.pages.automationexercise.AutomationExerciseCheckoutPage;
import org.example.model.pages.automationexercise.AutomationExerciseHomePage;
import org.example.model.pages.automationexercise.AutomationExerciseSecuredPage;
import org.example.model.pages.automationexercise.AutomationExerciseSignUpPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutomationExerciseTest extends SeleniumTest {

    @Test
    @DisplayName("Login")
    public void login() {
        AutomationExerciseHomePage home = new AutomationExerciseHomePage(driver).get();
        home.clickLogin();
    }

    @Test
    @DisplayName("Validate the signup page")
    public void validateSignup() {
        AutomationExerciseSignUpPage signup = new AutomationExerciseSignUpPage(driver).get();
        signup.startRegistration();
    }

    @Test
    @DisplayName("Navigate to the checkout")
    public void navigateToCheckout()  {
        AutomationExerciseCheckoutPage checkoutPage = new AutomationExerciseSecuredPage<AutomationExerciseCheckoutPage>(
                new AutomationExerciseCheckoutPage(driver),
                "tyler@qac.com", "password"
        ).get();

        checkoutPage.validateTitleContains("Checkout");
    }

}
