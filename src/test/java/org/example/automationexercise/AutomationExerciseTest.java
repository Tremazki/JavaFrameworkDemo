package org.example.automationexercise;

import org.example.junit.SeleniumTest;
import org.example.junit.extensions.DebugExtension;
import org.example.pages.automationexercise.AutomationExerciseCheckoutPage;
import org.example.pages.automationexercise.AutomationExerciseHomePage;
import org.example.pages.automationexercise.AutomationExerciseSecuredPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DebugExtension.class)
public class AutomationExerciseTest extends SeleniumTest {

    @Test
    @DisplayName("Login to Automation Exercise")
    public void automationExerciseTest()  {
        AutomationExerciseHomePage home = new AutomationExerciseHomePage(driver).get();

        home.clickLogin();

        assert driver.getTitle().contains("Signup");
    }

    @Test
    @DisplayName("Go to Checkout")
    public void automationExerciseTest2()  {
        AutomationExerciseSecuredPage checkoutPage = new AutomationExerciseSecuredPage(
                driver,
                new AutomationExerciseCheckoutPage(driver),
                "tyler@qac.com",
                "password").get();

        assert driver.getTitle().contains("Checkout");
    }

}
