package org.example.automationexercise;

import org.example.junit.SeleniumTest;
import org.example.junit.extensions.DebugExtension;
import org.example.model.pages.automationexercise.AutomationExerciseCheckoutPage;
import org.example.model.pages.automationexercise.AutomationExerciseHomePage;
import org.example.model.pages.automationexercise.AutomationExerciseSecuredPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DebugExtension.class)
public class AutomationExerciseTest extends SeleniumTest {

    @Test
    @DisplayName("Login to Automation Exercise")
    public void automationExerciseTest() throws Throwable {
        AutomationExerciseHomePage home = new AutomationExerciseHomePage(driver).get();

        home.clickLogin();
        home.assertTitle();
    }

    @Test
    @DisplayName("Go to Checkout")
    public void automationExerciseTest2()  {
        // Rework the secure page, looks weird and unwieldy
        AutomationExerciseCheckoutPage checkoutPage = new AutomationExerciseCheckoutPage(driver);
        AutomationExerciseSecuredPage  securedPage =  new AutomationExerciseSecuredPage(driver, checkoutPage, "tyler@qac.com", "password").get();

        checkoutPage.assertTitle();
    }

}
